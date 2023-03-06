package com.Ayano.springboot1.service;

import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskService {
    PageInfo<Task> selectTask(Integer supervisorId, Integer pageNum, Integer pageSize);

    List<Task> selectTaskOne(Integer taskId);

    List<Task> selectPlans(Integer taskId);

    PageInfo<User> selectEmployee(Integer supervisorId, Integer pageNum, Integer pageSize);

    List<User> selectEmployeeAll(Integer supervisorId);

    User selectEmployeeDetail(Integer employeeId);

    Integer addTask(Task task);

    Integer updateTaskStatus(Task task);

    Integer updateTask(Task task);

    Integer deleteTask(Task task);
}
