package com.Ayano.springboot1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class S_E {
    //supervisor（主管）对employee（员工）
    private Integer supervisorId;
    private Integer employeeId;

}
