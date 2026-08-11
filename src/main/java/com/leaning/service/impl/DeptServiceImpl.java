package com.leaning.service.impl;

import com.leaning.mapper.DeptMapper;
import com.leaning.mapper.EmpMapper;
import com.leaning.pojo.Dept;
import com.leaning.service.DeptService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {

        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {

        //统计该部门下员工数量
        Long count = empMapper.countEmpByDeptId(id);
        if(count > 0){
            //有员工，抛出运行时异常，携带提示消息
            throw new RuntimeException("对不起，当前部门下有员工，不能直接删除！");
        }
        //没有员工才删除部门
        deptMapper.deleteById(id);
    }

    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

    	deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {

        return deptMapper.findById(id);
    }

    @Override
    public void updata(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updata(dept);
    }
}
