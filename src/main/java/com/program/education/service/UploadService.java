package com.program.education.service;

import com.program.education.entity.Upload;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xihua
* @description 针对表【upload】的数据库操作Service
* @createDate 2024-04-19 11:18:10
*/
public interface UploadService {

    List<Integer> selectSubmitWork(int user_id);

    int addUpload(Upload upload);
}
