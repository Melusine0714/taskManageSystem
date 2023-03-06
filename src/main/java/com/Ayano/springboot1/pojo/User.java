package com.Ayano.springboot1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String userAccount;
    private String userPassword;
    private Integer userType;
    private String userName;
    private String userSex;
    private String userBirth;
    private Integer userDegree;
    private String userPosition;
    private String userBoardingTime;
    private String userExperience;

    private UserType type;
}
