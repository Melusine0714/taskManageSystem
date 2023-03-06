package com.Ayano.springboot1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer typeId;
    private String typeName;
}
