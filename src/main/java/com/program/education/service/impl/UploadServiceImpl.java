package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.Upload;
import com.program.education.service.UploadService;
import com.program.education.mapper.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xihua
* @description 针对表【upload】的数据库操作Service实现
* @createDate 2024-04-19 11:18:10
*/
@Service
public class UploadServiceImpl implements UploadService{

    @Autowired
    private UploadMapper uploadMapper;

    @Override
    public List<Integer> selectSubmitWork(int user_id) {
        return uploadMapper.selectSubmitWork(user_id);
    }
}




