package com.program.education.service.impl;

import com.program.education.entity.Askleave;
import com.program.education.service.LeaveService;
import com.program.education.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xihua
* @description 针对表【leave】的数据库操作Service实现
* @createDate 2024-04-19 19:19:24
*/
@Service
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<Askleave> selectAll(int offset, int limit) {
        return leaveMapper.selectAll(offset, limit);
    }

    @Override
    public int addLeave(Askleave leave) {
        return leaveMapper.addLeave(leave);
    }

    @Override
    public int deleteById(int id) {
        return leaveMapper.deleteById(id);
    }

    @Override
    public int updateStatus(int id, int status) {
        return leaveMapper.updateStatus(id, status);
    }
}




