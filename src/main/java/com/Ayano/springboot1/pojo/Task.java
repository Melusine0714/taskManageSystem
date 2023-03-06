package com.Ayano.springboot1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private Integer taskId;
    private String taskName;
    private String taskDescription;
    private String taskStartTime;
    private String taskEndTime;
    private Integer taskStatus;
    private Integer supervisorId;
    private Integer employeeId;

    private Plan plan;
    private User employee;
}