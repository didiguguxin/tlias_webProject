package com.leaning.service;

import com.leaning.pojo.ClazzOption;
import com.leaning.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    ClazzOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
