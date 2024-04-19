package com.program.education.controller;

import com.alibaba.fastjson.JSONObject;
import com.program.education.entity.News;
import com.program.education.entity.Page;
import com.program.education.entity.User;
import com.program.education.entity.Work;
import com.program.education.service.NewsService;
import com.program.education.service.UploadService;
import com.program.education.service.UserService;
import com.program.education.service.WorkService;
import com.program.education.utils.HostHolder;
import com.program.education.utils.Message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private WorkService workService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private HostHolder hostHolder;

    @Value("${server.servlet.context-path}")
    private String contextPath;


    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getFrontLogin() {
        return "site/front/login";
    }
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndex(Page page, Model model) {
        List<News> news = newsService.selectAll(page.getoffset(), page.getLimit());
        model.addAttribute("news",news);
        return "site/front/index";
    }

    @RequestMapping(path = "/work", method = RequestMethod.GET)
    public String getWork(Page page, Model model) {
        //获取作业列表
        List<Work> works = workService.selectAll(page.getoffset(), page.getLimit());
        List<Integer> all = new ArrayList<Integer>();
        for (Work work : works)
            all.add(work.getId());
        model.addAttribute("works", works);
        //获取用户已提交作业的列表
        User user = hostHolder.getUser();
        List<Integer> submit = uploadService.selectSubmitWork(user.getId());
        System.out.println(submit);
        model.addAttribute("submit", submit);
        model.addAttribute("works", works);
        return "site/front/work";
    }


//    @RequestMapping(path = "/index/data", method = RequestMethod.GET)
//    @ResponseBody
//    public String getIndexData(Page page) {
//        List<News> news = newsService.selectAll(page.getoffset(), page.getLimit());
//        return JSONObject.toJSONString(news);
//    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@Param("username") String username, @Param("password") String password, Model model, HttpServletResponse response) {
        User user = userService.selectByUsername(username, 1);
        Message message = new Message();
        if(user == null) {
            message.setCode(0);
            message.setMessage("账号或者密码错误!");
            model.addAttribute("loginMessage", message);
            return "site/front/login";
        }
        String real = user.getPassword();
        if(real.equals(password)) {
            Cookie cookie_userId = new Cookie("userId", user.getId().toString());
            cookie_userId.setPath(contextPath);
            response.addCookie(cookie_userId);
            message.setCode(1);
            message.setMessage("登录成功！");
            model.addAttribute("loginMessage", message);
            return "redirect:/front/index";
        } else {
            message.setCode(0);
            message.setMessage("账号或者密码错误!");
            model.addAttribute("loginMessage", message);
            return "site/front/login";
        }
    }

}
