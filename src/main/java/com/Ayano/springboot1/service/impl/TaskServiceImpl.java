package com.Ayano.springboot1.service.impl;

import com.Ayano.springboot1.mapper.PlanMapper;
import com.Ayano.springboot1.mapper.TaskMapper;
import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private PlanMapper planMapper;

    @Override
    public PageInfo<Task> selectTask(Integer supervisorId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Task> taskList = taskMapper.selectTask(supervisorId);
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        return pageInfo;
    }

    @Override
    public List<Task> selectTaskOne(Integer taskId) {
        return taskMapper.selectTaskOne(taskId);
    }

    @Override
    public List<Task> selectPlans(Integer taskId) {
        return taskMapper.selectPlans(taskId);
    }

    @Override
    public PageInfo<User> selectEmployee(Integer supervisorId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> taskList = taskMapper.selectEmployee(supervisorId);
        PageInfo<User> pageInfo = new PageInfo<>(taskList);
        return pageInfo;
    }

    @Override
    public List<User> selectEmployeeAll(Integer supervisorId) {
        return taskMapper.selectEmployee(supervisorId);
    }

    @Override
    public User selectEmployeeDetail(Integer employeeId) {
        return taskMapper.selectEmployeeDetail(employeeId);
    }

    @Override
    public Integer addTask(Task task) {
        return taskMapper.insertTask(task);
    }

    @Override
    public Integer updateTaskStatus(Task task) {
        return taskMapper.updateTaskStatus(task);
    }

    @Override
    public Integer updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    @Override
    public Integer deleteTask(Task task) {
        Plan plan = new Plan();
        plan.setTaskId(task.getTaskId());
        Integer result1 = planMapper.deletePlan(plan);
        Integer result2 = taskMapper.deleteTask(task);
        if (result1 == 1 && result2 == 1) {
            return 1;
        }else {
            return 0;
        }
    }


}
