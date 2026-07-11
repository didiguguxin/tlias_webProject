package com.leaning.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaning.mapper.EmpMapper;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import com.leaning.pojo.PageResult;
import com.leaning.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

   /*
   ----------------------------------------原始分页查询语句---------------------------------------------------
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

    */

   /*
   @Override
    public PageResult<Emp> page(Integer page, Integer pageSize,
                                String name, Integer gender,
                                LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //查询当前页数据
        List<Emp> empList=empMapper.list(name,gender,begin,end);
        //获取总记录数，封装结果
        Page<Emp> p= (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    */

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //查询当前页数据
        List<Emp> empList=empMapper.list(empQueryParam);
        //获取总记录数，封装结果
        Page<Emp> p= (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }


}
