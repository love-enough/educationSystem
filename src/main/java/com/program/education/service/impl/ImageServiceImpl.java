package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.Image;
import com.program.education.service.ImageService;
import com.program.education.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【image】的数据库操作Service实现
 * @createDate 2024-04-16 16:28:19
 */
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageMapper imageMapper;
    @Override
    public List<Image> selectAll() {
        return imageMapper.selectAll();
    }

    @Override
    public int updateStatus(int status, int id) {
        return imageMapper.updateStatus(status, id);
    }

    @Override
    public int addImage(Image image) {
        return imageMapper.addImage(image);
    }
}