package com.leaning.service;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import com.leaning.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);



}
