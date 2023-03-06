package com.Ayano.springboot1.service.impl;

import com.Ayano.springboot1.mapper.SEMapper;
import com.Ayano.springboot1.mapper.UserMapper;
import com.Ayano.springboot1.pojo.S_E;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SEMapper seMapper;

    @Override
    public User selectUser(String account, String password) {
        return userMapper.selectUser(account, password);
    }

    @Override
    public User selectUserOne(User user) {
        return userMapper.selectUserOne(user);
    }

    @Override
    public PageInfo<User> selectUserAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectUserAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    public List<User> selectSupervisorAll() {
        return userMapper.selectSupervisorAll();
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer updateUser(User user, S_E s_e) {
        //定义局部变量
        int result1;
        int result2;
        //先查询有无上下属关系
        S_E result = seMapper.selectSE(s_e.getSupervisorId(), s_e.getEmployeeId());
        //没有则插入，有就更新
        if (result == null) {
            //返回插入结果
            result1 = seMapper.insertSE(s_e.getSupervisorId(), s_e.getEmployeeId());
        } else {
            //返回更新结果
            result1 = seMapper.updateSE(s_e.getSupervisorId(), s_e.getEmployeeId());
        }
        result2 = userMapper.updateUser(user);
        if (result1 == 1 && result2 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteUser(Integer id) {
        Integer result1 = seMapper.deleteSE(id);
        Integer result2 = userMapper.deleteUser(id);
        if (result1 == 1 && result2 == 1) {
            return 1;
        }else {
            return 0;
        }
    }
}
