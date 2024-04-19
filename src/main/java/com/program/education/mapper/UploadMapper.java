package com.program.education.mapper;

import com.program.education.entity.Upload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author xihua
* @description 针对表【upload】的数据库操作Mapper
* @createDate 2024-04-19 11:18:10
* @Entity com.program.education.entity.Upload
*/

@Mapper
public interface UploadMapper {

    List<Integer> selectSubmitWork(int user_id);

    int addUpload(Upload upload);
}




