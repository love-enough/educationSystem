package com.program.education.controller;


import com.program.education.entity.Page;
import com.program.education.entity.Video;
import com.program.education.service.VideoService;
import com.program.education.utils.HostHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/videoList", method = RequestMethod.GET)
    public String getVideo(Model model, Page page) {
        page.setRows(videoService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/video/videoList");
        List<Video> videos = videoService.selectAll(page.getoffset(), page.getLimit());
        model.addAttribute("videoList", videos);
        return "site/video";
    }
    @RequestMapping(path = "/addVideo", method = RequestMethod.GET)
    public String getAddVideo(Model model) {
        return "site/addVideo";
    }

    @RequestMapping(path = "/addVideo", method = RequestMethod.POST)
    public String addVideo(@Param("description") String description, @Param("url") String url) {
        Video video = new Video();
        video.setTeacher_id(1);
        video.setDescription(description);
        video.setUrl(url);
        video.setCreateTime(new Date());
        int result = videoService.addVideo(video);
        return "redirect:/video/videoList";
    }

    @RequestMapping(path = "/deleteVideo", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVideo(@Param("id") int id) {
        int result = videoService.deleteById(id);
        return "success!";
    }
}
