package com.program.education.mapper;

import com.program.education.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
* @author xihua
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-14 16:56:30
* @Entity com.program.education.entity.User
*/
@Mapper
public interface UserMapper{
    User selectById(@Param("id") int id);
    User selectByUsername(@Param("username") String username, @Param("type") int type);
    User selectByUsernameAndPasswordAndType(String username, String password);
    int insertUser(User user);

    int deleteById(int id);

    int updatePassword(int id, String password);

    int updateUser(@Param("username") String username, @Param("password") String password, @Param("name") String name, @Param("sex") int sex, @Param("id") int id);
    List<User> selectUserList(@Param("type") int type);

    List<User> selectUsers(@Param("type") int type, @Param("offset") int offset, @Param("limit") int limit);
}




