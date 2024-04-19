package com.program.education.controller;

import com.alibaba.fastjson.JSONObject;
import com.program.education.entity.*;
import com.program.education.service.*;
import com.program.education.utils.HostHolder;
import com.program.education.utils.Message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ls.LSInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AskforleaveService askforleaveService;

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
        page.setRows(workService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/front/work");
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

    @RequestMapping(path = "/leave", method = RequestMethod.GET)
    public String getLeave(Page page, Model model) {
        User user = hostHolder.getUser();
        page.setRows(askforleaveService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/front/leave");
        List<Askforleave> askleaves = askforleaveService.seletAllById(user.getId(), page.getoffset(), page.getLimit());
        model.addAttribute("leaveList", askleaves);
        return "site/front/leaveHistory";
    }
    @RequestMapping(path = "/ask", method = RequestMethod.GET)
    public String GetAsk() {
        return "site/front/leave";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadWork(@Param("work_id") int work_id, @Param("file") MultipartFile file, HttpServletRequest request, Model model) {
        Message message = new Message();
        Upload upload = new Upload();
        if(file == null) {
            upload.setWork_id(work_id);
            upload.setUser_id(hostHolder.getUser().getId());
            upload.setFile("null");
            upload.setCreateTime(new Date());
            int result = uploadService.addUpload(upload);
            if(result == 0) {
                message.setCode(0);
                message.setMessage("上传失败！");
                return "redirect:/front/work";
            }
            message.setCode(1);
            message.setMessage("上传成功！");
            model.addAttribute("message", message);
            return "redirect:/front/work";
        }
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            System.out.println(file.getContentType() + "---" + file.getName() + "---" + file.getOriginalFilename());
            Path path = Paths.get("src/main/resources/static/studentFile/" + file.getOriginalFilename());
            System.out.println(path);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        upload.setWork_id(work_id);
        upload.setUser_id(hostHolder.getUser().getId());
        upload.setCreateTime(new Date());
        upload.setFile("src/main/resources/static/studentFile/" + file.getOriginalFilename());
        int result = uploadService.addUpload(upload);
        if(result == 0) {
            message.setCode(0);
            message.setMessage("上传失败！");
            return "redirect:/front/work";
        }
        message.setCode(1);
        message.setMessage("上传成功！");
        model.addAttribute("message", message);
        return "redirect:/front/work";
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
