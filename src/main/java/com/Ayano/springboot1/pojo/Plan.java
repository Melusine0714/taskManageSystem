package com.Ayano.springboot1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan implements Serializable {
    private Integer planId;
    private String planName;
    private String planDescription;
    private Integer planEmployee;
    private String planStartTime;
    private String planEndTime;
    private Integer planStatus;
    private Integer planIsFeedback;
    private String planFeedback;
    private Integer taskId;

    private User employee;
}
