package com.Ayano.springboot1.mapper;

import com.Ayano.springboot1.pojo.S_E;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SEMapper {
    S_E selectSE(Integer supervisorId,Integer employeeId);
    Integer insertSE(Integer supervisorId,Integer employeeId);
    Integer updateSE(Integer supervisorId,Integer employeeId);
    Integer deleteSE(Integer employeeId);
}
