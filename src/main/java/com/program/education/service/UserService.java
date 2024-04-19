package com.program.education.service;

import com.program.education.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xihua
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-04-14 16:56:30
 */

public interface UserService{
    User selectById(int id);

    int insertUser(User user);

    int deleteUserById(int id);

    int updatePassword(int id, String password);

    int updateUser(String username, String password, String name, int sex, int id);
    User selectByUsername(String username, int type);

    List<User> selectUserList(int type);

    List<User> selectUsers(int type, int offset, int limit);
}