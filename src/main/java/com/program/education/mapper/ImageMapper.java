package com.program.education.mapper;

import com.program.education.entity.Image;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【image】的数据库操作Mapper
 * @createDate 2024-04-16 16:28:19
 * @Entity com.program.education.entity.Image
 */

@Mapper
public interface ImageMapper {
    List<Image> selectAll();

    int updateStatus(@Param("id") int id, @Param("status") int status);

    int addImage(Image image);

}