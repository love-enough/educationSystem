package com.program.education.mapper;

import com.program.education.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【news】的数据库操作Mapper
 * @createDate 2024-04-16 16:28:19
 * @Entity com.program.education.entity.News
 */
@Mapper
public interface NewsMapper {

    List<News> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int addNews(News news);

    int deleteById(int id);
}