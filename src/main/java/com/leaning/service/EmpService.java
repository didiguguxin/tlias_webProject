package com.leaning.service;

import com.leaning.pojo.Emp;
import com.leaning.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
