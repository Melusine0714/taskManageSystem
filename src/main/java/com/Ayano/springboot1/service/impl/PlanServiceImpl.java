package com.Ayano.springboot1.service.impl;

import com.Ayano.springboot1.mapper.PlanMapper;
import com.Ayano.springboot1.mapper.TaskMapper;
import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.PlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    private PlanMapper planMapper;

    @Override
    public List<Task> selectTaskOne(Integer taskId) {
        return planMapper.selectTaskOne(taskId);
    }
    @Override
    public List<Task> selectTaskList(User user) {
        return planMapper.selectTask(user);
    }

    @Override
    public PageInfo<Task> selectTask(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Task> taskList = planMapper.selectTask(user);
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        return pageInfo;
    }

    @Override
    public PageInfo<Plan> selectPlans(Plan plan, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Plan> planList = planMapper.selectPlans(plan);
        PageInfo<Plan> pageInfo = new PageInfo<>(planList);
        return pageInfo;
    }

    @Override
    public List<Plan> selectTask_Plans(Integer taskID) {
        return planMapper.selectTask_Plans(taskID);
    }

    @Override
    public Integer addPlan(Plan plan) {
        return planMapper.insertPlan(plan);
    }

    @Override
    public Integer updatePlan(Plan plan) {
        return planMapper.updatePlan(plan);
    }

    @Override
    public Integer deletePlan(Plan plan) {
        return planMapper.deletePlan(plan);
    }


}