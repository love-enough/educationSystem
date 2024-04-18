package com.program.education.service;

import com.program.education.entity.Work;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
* @author xihua
* @description 针对表【work】的数据库操作Service
* @createDate 2024-04-16 16:28:19
*/
public interface WorkService {
    List<Work> selectAll(int offset, int limit);

    Work selectById(int id);

    int addWork(Work work);

    int deleteById(int id);
}
