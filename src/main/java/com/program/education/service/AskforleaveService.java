package com.program.education.service;

import com.program.education.entity.Askforleave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.program.education.entity.Askleave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xihua
* @description 针对表【askforleave】的数据库操作Service
* @createDate 2024-04-19 20:23:50
*/
public interface AskforleaveService {
    List<Askforleave> selectAll(int offset, int limit);

    int addLeave(Askforleave askforleave);

    int deleteById(int id);
    int updateStatus(int id, int status);

    List<Askforleave> seletAllById(@Param("user_id") int user_id, @Param("offset") int offset, @Param("limit") int limit);

}
