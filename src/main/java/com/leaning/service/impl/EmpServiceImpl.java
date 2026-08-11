package com.leaning.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leaning.mapper.EmpExprMapper;
import com.leaning.mapper.EmpMapper;

import com.leaning.pojo.*;
import com.leaning.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

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


    //分页查询
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Emp emp) {
        //员工基本信息
        emp.setCreateTime(LocalDateTime.now()); //创建时间
        emp.setUpdateTime(LocalDateTime.now());//修改时间
        empMapper.add(emp);//调用Mapper接口，添加员工
        //员工工作经历
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){

           for(EmpExpr empExpr:exprList){
               empExpr.setEmpId(emp.getId());
           }
            empExprMapper.insertBatch(exprList);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteById(ids);
        empExprMapper.deleteByEmpId(ids);

    }

    @Override
    public Emp getById(Integer id) {
        Emp emp=empMapper.getById(id);
        return emp;
    }

    @Transactional
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        List<Emp> list = empMapper.listAll();
        return list;
    }
}
