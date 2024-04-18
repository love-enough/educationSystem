package com.program.education.controller;

import com.alibaba.fastjson.JSONObject;
import com.program.education.service.UserService;
import com.program.education.service.VideoService;
import com.program.education.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkService workService;

    @Autowired
    private VideoService videoService;

    @RequestMapping(path = "/data", method = RequestMethod.GET)
    @ResponseBody
    public String getData() {
        int userNum = userService.selectUserList(1).size();
        int teacherNum = userService.selectUserList(2).size();
        int adminNum = userService.selectUserList(3).size();
        JSONObject json = new JSONObject();
        json.put("userNum", userNum);
        json.put("teacherNum", teacherNum);
        json.put("adminNum", adminNum);
        return json.toJSONString();
    }


}
