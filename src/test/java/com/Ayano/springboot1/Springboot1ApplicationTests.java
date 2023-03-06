package com.Ayano.springboot1;

import com.Ayano.springboot1.mapper.PlanMapper;
import com.Ayano.springboot1.mapper.TaskMapper;
import com.Ayano.springboot1.mapper.UserMapper;
import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.pojo.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Springboot1ApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private PlanMapper planMapper;

    @Test
    public void selectUser() {
        User user = userMapper.selectUser("wza", "123");
        System.out.println(user);
    }

    @Test
    public void addUser() {
        UserType userType = null;
        User user = new User(0, "Melusine", "Ayano2002", 3, "吴泽安", "男", "2002-08-18", 2, "运维", "2023-02-23", "", userType);
        System.out.println(userMapper.insertUser(user));
    }

    @Test
    public void selectTask() {
        System.out.println(taskMapper.selectTask(3));
    }

    @Test
    public void selectTask_Plans() {
        List<Task> taskList = taskMapper.selectTaskOne(1);
    }
}