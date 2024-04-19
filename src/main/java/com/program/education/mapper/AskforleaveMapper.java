package com.program.education.mapper;

import com.program.education.entity.Askforleave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.program.education.entity.Askleave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xihua
* @description 针对表【askforleave】的数据库操作Mapper
* @createDate 2024-04-19 20:23:50
* @Entity com.program.education.entity.Askforleave
*/

@Mapper
public interface AskforleaveMapper {
    List<Askforleave> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int addLeave(Askforleave askforleave);

    int deleteById(int id);
    int updateStatus(@Param("id") int id, @Param("status") int status);

    List<Askforleave> seletAllById(@Param("user_id") int user_id, @Param("offset") int offset, @Param("limit") int limit);

}




