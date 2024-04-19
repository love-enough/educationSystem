package com.program.education.controller;

import com.program.education.entity.News;
import com.program.education.entity.Page;
import com.program.education.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/newsList", method = RequestMethod.GET)
    public String getNews(Model model, Page page) {
        page.setRows(newsService.selectAll(page.getoffset(), page.getLimit()).size());
        page.setPath("/news/newsList");
        List<News> news = newsService.selectAll(page.getoffset(), page.getLimit());
        model.addAttribute("newsList", news);
        return "site/news";
    }

    @RequestMapping(path = "/addNews", method = RequestMethod.GET)
    public String addNews(Model model) {
        return "site/addNews";
    }

    @RequestMapping(path = "/addNews", method = RequestMethod.POST)
    public String addNews(@Param("title") String title, @Param("content") String content, Model model) {
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCreateTime(new Date());
        news.setAdmin_id(1);
        int result = newsService.addNews(news);
        return "redirect:/news/newsList";
    }
}
