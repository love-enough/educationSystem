package com.program.education.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.program.education.entity.User;
import com.program.education.service.UserService;
import com.program.education.utils.CookieUtil;
import com.program.education.utils.HostHolder;
import com.program.education.utils.Message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        return "/index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }

    @RequestMapping(path = "/table", method = RequestMethod.GET)
    public String getTablePage() {
        return "/site/table";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@Param("username") String username, String password, @Param("type") int type, Model model, HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(type);
        User user = userService.selectByUsername(username, type);
        System.out.println(user);
        Message message = new Message();
        if(user == null) {
            message.setCode(0);
            message.setMessage("账号或者密码错误!");
            model.addAttribute("loginMessage", message);
            return "site/login";
        }
        String real = user.getPassword();
        if(real.equals(password)) {
            Cookie cookie_userId = new Cookie("userId", user.getId().toString());
            cookie_userId.setPath(contextPath);
            response.addCookie(cookie_userId);
            message.setCode(1);
            message.setMessage("登录成功！");
            model.addAttribute("loginMessage", message);
            return "redirect:/index";
        } else {
            message.setCode(0);
            message.setMessage("账号或者密码错误!");
            model.addAttribute("loginMessage", message);
            return "site/login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.delCookie(request, response, "userId");
        return "redirect:/login";
    }

}
