package com.leaning.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaning.mapper.StudentMapper;
import com.leaning.pojo.Clazz;
import com.leaning.pojo.PageResult;
import com.leaning.pojo.Student;
import com.leaning.pojo.StudentQueryParam;
import com.leaning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired

    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
            //设置分页参数
            PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
            //条件查询学生，关联班级拿到clazzName
            List<Student> studentList = studentMapper.list(studentQueryParam);
            //封装分页信息
            PageInfo<Student> pageInfo = new PageInfo<>(studentList);
            return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
        }


    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void updata(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updata(student);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
            //1. 根据id查询学员
            Student student = studentMapper.findById(id);
            //2. 违纪次数 +1
            student.setViolationCount(student.getViolationCount()+1);
            //3. 违纪扣分累加
            student.setViolationScore(student.getViolationScore()+score);
            //4. 更新时间
            student.setUpdateTime(LocalDateTime.now());
            //5. 更新数据库
            studentMapper.updata(student);

    }
}
