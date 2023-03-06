package com.Ayano.springboot1.service;

import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PlanService {
    List<Task> selectTaskOne(Integer taskId);

    List<Task> selectTaskList(User user);

    PageInfo<Task> selectTask(User user, Integer pageNum, Integer pageSize);

    PageInfo<Plan> selectPlans(Plan plan, Integer pageNum, Integer pageSize);

    List<Plan> selectTask_Plans(Integer taskId);

    Integer addPlan(Plan plan);

    Integer updatePlan(Plan plan);

    Integer deletePlan(Plan plan);
}
