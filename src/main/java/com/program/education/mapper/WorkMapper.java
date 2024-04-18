package com.program.education.mapper;

import com.program.education.entity.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* @author xihua
* @description 针对表【work】的数据库操作Mapper
* @createDate 2024-04-16 16:28:19
* @Entity com.program.education.entity.Work
*/

@Mapper
public interface WorkMapper {

    List<Work> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    Work selectById(int id);

    int addWork(Work work);

    int deleteWork(int id);
}




