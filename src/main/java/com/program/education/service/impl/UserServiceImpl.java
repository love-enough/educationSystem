package com.program.education.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.program.education.entity.User;
import com.program.education.service.UserService;
import com.program.education.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author xihua
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-14 16:56:30
*/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteById(id);
    }


    @Override
    public int updatePassword(int id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    public int updateUser(String username, String password, String name, int sex, int id) {
        return userMapper.updateUser(username, password, name, sex, id);
    }


    @Override
    public User selectByUsername(String username, int type) {
        return userMapper.selectByUsername(username, type);
    }

    @Override
    public List<User> selectUserList(int type) {
        return userMapper.selectUserList(type);
    }


}




