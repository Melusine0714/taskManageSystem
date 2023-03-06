package com.Ayano.springboot1.mapper;

import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectTask(Integer supervisorId);

    List<Task> selectTaskOne(Integer taskId);

    List<Task> selectPlans(Integer taskId);

    List<User> selectEmployee(Integer supervisorId);

    User selectEmployeeDetail(Integer employeeId);

    Integer insertTask(Task task);

    Integer updateTaskStatus(Task task);

    Integer updateTask(Task task);

    Integer deleteTask(Task task);
}
