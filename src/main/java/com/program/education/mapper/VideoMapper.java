package com.program.education.mapper;

import com.program.education.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【video】的数据库操作Mapper
 * @createDate 2024-04-16 16:28:19
 * @Entity com.program.education.entity.Video
 */
@Mapper
public interface VideoMapper {
    List<Video> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int addVideo(Video video);

    int deleteById(int id);
}