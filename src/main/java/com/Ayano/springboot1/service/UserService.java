package com.Ayano.springboot1.service;

import com.Ayano.springboot1.pojo.S_E;
import com.Ayano.springboot1.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    User selectUser(String account, String password);

    User selectUserOne(User user);

    PageInfo<User> selectUserAll(Integer pageNum, Integer pageSize);

    List<User> selectSupervisorAll();

    Integer addUser(User user);

    Integer updateUser(User user, S_E s_e);

    Integer deleteUser(Integer id);
}
