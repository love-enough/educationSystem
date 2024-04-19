package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.Askforleave;
import com.program.education.entity.Askleave;
import com.program.education.mapper.LeaveMapper;
import com.program.education.service.AskforleaveService;
import com.program.education.mapper.AskforleaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xihua
* @description 针对表【askforleave】的数据库操作Service实现
* @createDate 2024-04-19 20:23:50
*/
@Service
public class AskforleaveServiceImpl implements AskforleaveService{

    @Autowired
    private AskforleaveMapper askforleaveMapper;

    @Override
    public List<Askforleave> selectAll(int offset, int limit) {
        return askforleaveMapper.selectAll(offset, limit);
    }

    @Override
    public int addLeave(Askforleave askforleave) {
        return askforleaveMapper.addLeave(askforleave);
    }

    @Override
    public int deleteById(int id) {
        return askforleaveMapper.deleteById(id);
    }

    @Override
    public int updateStatus(int id, int status) {
        return askforleaveMapper.updateStatus(id, status);
    }

    @Override
    public List<Askforleave> seletAllById(int user_id, int offset, int limit) {
        return askforleaveMapper.seletAllById(user_id, offset, limit);
    }
}




