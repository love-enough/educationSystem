package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.Video;
import com.program.education.service.VideoService;
import com.program.education.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xihua
* @description 针对表【video】的数据库操作Service实现
* @createDate 2024-04-16 16:28:19
*/
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectAll(int offset, int limit) {
        return videoMapper.selectAll(offset, limit);
    }

    @Override
    public int addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public int deleteById(int id) {
        return videoMapper.deleteById(id);
    }
}




