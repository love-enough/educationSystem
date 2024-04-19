package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.Work;
import com.program.education.service.WorkService;
import com.program.education.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【work】的数据库操作Service实现
 * @createDate 2024-04-16 16:28:19
 */
@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkMapper workMapper;

    @Override
    public List<Work> selectAll(int offset, int limit) {
        return workMapper.selectAll(offset, limit);
    }

    @Override
    public Work selectById(int id) {
        return workMapper.selectById(id);
    }

    @Override
    public int addWork(Work work) {
        return workMapper.addWork(work);
    }

    @Override
    public int deleteById(int id) {
        return workMapper.deleteWork(id);
    }
}