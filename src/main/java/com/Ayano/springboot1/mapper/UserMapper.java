package com.Ayano.springboot1.mapper;

import com.Ayano.springboot1.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUser(String account, String password);

    User selectUserOne(User user);

    List<User> selectUserAll();

    List<User> selectSupervisorAll();

    Integer insertUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);
}
