package com.Ayano.springboot1.mapper;

import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    List<Task> selectTaskOne(Integer taskId);
    List<Plan> selectPlans(Plan plan);

    List<Task> selectTask(User user);

    List<Plan> selectTask_Plans(Integer taskId);

    Integer insertPlan(Plan plan);

    Integer updatePlan(Plan plan);

    Integer deletePlan(Plan plan);
}
