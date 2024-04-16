package com.program.education.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.program.education.entity.User;
import com.program.education.service.UserService;
import com.program.education.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage() {
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
    public String login(@Param("username") String username, String password, @Param("type") int type, Model model) {
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
}
