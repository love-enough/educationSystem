package com.program.education.service;

import com.program.education.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xihua
* @description 针对表【image】的数据库操作Service
* @createDate 2024-04-16 16:28:19
*/
public interface ImageService {
    List<Image> selectAll();

    int updateStatus(int status, int id);

    int addImage(Image image);


}
