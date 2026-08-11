package com.leaning.service.impl;

import com.leaning.mapper.EmpMapper;
import com.leaning.mapper.StudentMapper;
import com.leaning.pojo.ClazzOption;
import com.leaning.pojo.JobOption;
import com.leaning.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("job")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {

        return empMapper.countEmpGenderData();
    }

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ClazzOption getStudentCountData() {
        //查询：每个班级名称 + 对应人数
        List<Map<String, Object>> list = studentMapper.countStudentByClazz();

        //取出班级名称 → clazzList
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        //取出人数 → dataList
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentByDegree();
    }
}
