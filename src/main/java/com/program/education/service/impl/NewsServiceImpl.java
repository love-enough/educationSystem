package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.News;
import com.program.education.service.NewsService;
import com.program.education.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xihua
* @description 针对表【news】的数据库操作Service实现
* @createDate 2024-04-16 16:28:19
*/
@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public List<News> selectAll() {
        return newsMapper.selectAll();
    }

    @Override
    public int addNews(News news) {
        return newsMapper.addNews(news);
    }
}




