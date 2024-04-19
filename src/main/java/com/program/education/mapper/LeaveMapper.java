package com.program.education.mapper;

import com.program.education.entity.Askleave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xihua
* @description 针对表【leave】的数据库操作Mapper
* @createDate 2024-04-19 19:19:24
* @Entity com.program.education.entity.Leave
*/

@Mapper
public interface LeaveMapper {

    List<Askleave> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int addLeave(Askleave leave);

    int deleteById(int id);
    int updateStatus(int id, int status);
}




