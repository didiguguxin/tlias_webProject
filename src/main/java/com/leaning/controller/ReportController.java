package com.leaning.controller;

import com.leaning.pojo.ClazzOption;
import com.leaning.pojo.JobOption;
import com.leaning.pojo.Result;
import com.leaning.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.JobKOctets;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    // 获取员工职位数据
    @GetMapping("empJobData")
    public Result getEmpJobData() {
        log.info("获取员工职位数据");
        JobOption joboption=reportService.getEmpJobData();
        return Result.success(joboption);
    }

    // 获取员工性别数据
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("获取员工性别数据");
        List<Map<String, Object>> list =reportService.getEmpGenderData();
        return Result.success(list);
    }

    //班级人数统计
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("获取班级人数统计数据");
        ClazzOption clazzOption =reportService.getStudentCountData();
        return Result.success(clazzOption);
    }

    //学员学历统计
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("获取学员学历统计数据");
        List<Map<String, Object>> list =reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
