package com.leaning.service;

import com.leaning.pojo.Dept;
import com.leaning.pojo.PageResult;
import com.leaning.pojo.Student;
import com.leaning.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student findById(Integer id);

    void updata(Student student);

    void violationHandle(Integer id, Integer score);
}
