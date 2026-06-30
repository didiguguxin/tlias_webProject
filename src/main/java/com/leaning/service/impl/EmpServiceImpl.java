package com.leaning.service.impl;

import com.leaning.mapper.EmpMapper;

import com.leaning.pojo.Emp;
import com.leaning.pojo.PageResult;
import com.leaning.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用Mapper接口，查询总记录数
        long total=empMapper.count();

        //调用Mapper接口，查询当前页数据
        Integer start=(page-1)*pageSize;
        List<Emp> rows=empMapper.list(start,pageSize);

        //封装结果 pageResult
        return new PageResult<Emp>(total,rows);
    }
}
