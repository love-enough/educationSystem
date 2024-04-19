package com.program.education.controller;

import com.program.education.entity.Page;
import com.program.education.entity.User;
import com.program.education.service.UserService;
import com.program.education.utils.HostHolder;
import com.program.education.utils.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/userList", method = RequestMethod.GET)
    public String getUserListPage(Model model, Page page) {
        page.setRows(userService.selectUserList(1).size());
        page.setPath("/user/userList");
        List<User> users = userService.selectUsers(1, page.getoffset(), page.getLimit());
        model.addAttribute("userList", users);
        return "site/user";
    }


    @RequestMapping(path = "/teacherList", method = RequestMethod.GET)
    public String getTeacherListPage(Model model, Page page) {
        page.setRows(userService.selectUserList(2).size());
        page.setPath("/user/teacherList");
        List<User> users = userService.selectUsers(2, page.getoffset(), page.getLimit());
        model.addAttribute("userList", users);
        return "site/teacher";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.GET)
    public String getAddUser(Model model) {
        return "site/editStudent";
    }
    @RequestMapping(path = "/addTeacher", method = RequestMethod.GET)
    public String getAddTeacher(Model model) {
        return "site/editTeacher";
    }

    @RequestMapping(path = "/edit/{userId}", method = RequestMethod.GET)
    public String getEdit(@PathVariable("userId") int userId, Model model) {
        User user = userService.selectById(userId);
        model.addAttribute("id", userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("name", user.getName());
        model.addAttribute("sex", user.getSex());
        model.addAttribute("type", user.getType());
        return "site/edit";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String addUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("name") String name,
            @Param("sex") int sex,
            @Param("type") int type,
            Model model) {
        Message message = new Message();
        if(userService.selectByUsername(username, type) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setSex(sex);
            user.setName(name);
            user.setType(type);
            user.setCreateTime(new Date());
            int result = userService.insertUser(user);
            if(result == 1) {
                message.setCode(1);
                message.setMessage("注册成功");
                model.addAttribute("message", message);
                if(type == 1)
                    return "redirect:/user/userList";
                else
                    return "redirect:/user/teacherList";
            }
            message.setCode(0);
            message.setMessage("注册出现异常");
            model.addAttribute("message", message);
            if(type == 1)
                return "site/editStudent";
            else
                return "site/editTeacher";
        }
        message.setCode(0);
        message.setMessage("已存在该账号");
        model.addAttribute("message", message);
        if(type == 1)
            return "site/editStudent";
        else
            return "site/editTeacher";
    }

    @RequestMapping(path = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@Param("id") int id) {
        int result = userService.deleteUserById(id);
        return "success!";
    }

    @RequestMapping(path = "/deleteTeacher", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTeacher(@Param("id") int id) {
        int result = userService.deleteUserById(id);
        return "success!";
    }

    @RequestMapping(path = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(String username, String password, String name, int sex, int id) {
        System.out.println(username + "--" +password + "--" +name + "--" + sex + " " + id);
//        int result = userService.updateUser(username, password, name, sex, id);
//        if(result == 1) {
//            return "redirect:/edit/{id}";
//        } else {
//            return "更新失败!";
//        }
        return "1";
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("userId")int userId) {
        return userService.selectById(userId);
    }

    @RequestMapping(path = "/{username}/{password}/{name}/{sex}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String insertUser(
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @PathVariable("name") String name,
            @PathVariable("sex") int sex,
            @PathVariable("type") int type) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setType(type);
        user.setCreateTime(new Date());
        int result =  userService.insertUser(user);
        if(result == 1)
            return "success!";
        return "error!";
    }
}
