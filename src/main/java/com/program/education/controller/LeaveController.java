package com.program.education.controller;

import com.program.education.entity.Askforleave;
import com.program.education.entity.Askleave;
import com.program.education.entity.Page;
import com.program.education.entity.User;
import com.program.education.service.AskforleaveService;
import com.program.education.service.LeaveService;
import com.program.education.utils.HostHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private AskforleaveService askforleaveService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/leaveList", method = RequestMethod.GET)
    public String getLeave(Page page, Model model) {
        page.setRows(askforleaveService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/leave/leaveList");
        List<Askforleave> askforleaves = askforleaveService.selectAll(page.getoffset(), page.getLimit());
        model.addAttribute("leaveList", askforleaves);
        return "site/leave";
    }

    @RequestMapping(path = "/agree", method = RequestMethod.POST)
    public String agree(@Param("id") int id) {
        askforleaveService.updateStatus(id, 1);
        return "redirect:/leave/leaveList";
    }

    @RequestMapping(path = "/disagree", method = RequestMethod.POST)
    public String disagree(@Param("id") int id) {
        askforleaveService.updateStatus(id, 2);
        return "redirect:/leave/leaveList";
    }

    @RequestMapping(path = "/ask", method = RequestMethod.POST)
    public String ask(@RequestParam("user_id") int user_id, @RequestParam("fromTime") String fTime, @RequestParam("toTime") String tTime, @RequestParam("content") String content) throws ParseException {
        User user = hostHolder.getUser();
        Askforleave askforleave = new Askforleave();
        askforleave.setStatus(0);
        askforleave.setUser_id(user_id);
        askforleave.setContent(content);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromTime = format.parse(fTime.replace("T"," "));
        Date toTime = format.parse(tTime.replace("T"," "));
        askforleave.setFromTime(fromTime);
        askforleave.setToTime(toTime);
        askforleave.setCreateTime(new Date());
        askforleave.setTeacher_id(0);
        askforleaveService.addLeave(askforleave);
        return "redirect:/front/leave";
    }
}
