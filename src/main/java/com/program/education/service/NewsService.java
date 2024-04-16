package com.program.education.service;

import com.program.education.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xihua
* @description 针对表【news】的数据库操作Service
* @createDate 2024-04-16 16:28:19
*/
public interface NewsService {
    List<News> selectAll();

    int addNews(News news);
}
