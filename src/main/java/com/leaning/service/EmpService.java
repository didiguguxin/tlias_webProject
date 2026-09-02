package com.leaning.service;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import com.leaning.pojo.LoginInfo;
import com.leaning.pojo.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);


    void delete(List<Integer> ids);


    Emp getById(Integer id);


    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);


}
