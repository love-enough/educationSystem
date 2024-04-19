package com.program.education.service;

import com.program.education.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【video】的数据库操作Service
 * @createDate 2024-04-16 16:28:19
 */
public interface VideoService {

    List<Video> selectAll(int offset, int limit);

    int addVideo(Video video);

    int deleteById(int id);
}