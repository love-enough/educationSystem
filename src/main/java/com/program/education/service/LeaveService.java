package com.program.education.service;

import com.program.education.entity.Askleave;

import java.util.List;

/**
* @author xihua
* @description 针对表【leave】的数据库操作Service
* @createDate 2024-04-19 19:19:24
*/
public interface LeaveService{

    List<Askleave> selectAll(int offset, int limit);

    int addLeave(Askleave leave);

    int deleteById(int id);
    int updateStatus(int id, int status);
}
