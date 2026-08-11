package com.leaning.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaning.mapper.ClazzMapper;
import com.leaning.pojo.Clazz;
import com.leaning.pojo.ClazzQueryParam;
import com.leaning.pojo.PageResult;
import com.leaning.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //查询当前页数据
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 循环计算班级状态status
        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if(now.isBefore(beginDate)){
                clazz.setStatus("未开班");
            }else if (now.isAfter(endDate)){
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("在读");
            }
        }
        //获取总记录数，封装结果
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);

    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);

    }

    @Override
    public void updata(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updata(clazz);
    }

    @Override
    public List<Clazz> list() {
        List<Clazz> list = clazzMapper.listAll();
        return list;
    }
}


