package com.leaning.controller;

import com.leaning.pojo.*;
import com.leaning.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 日志
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //4.1 学员列表查询
    @GetMapping
        public Result page(StudentQueryParam studentQueryParam) {
            log.info("查询"+ studentQueryParam +"班级列表");
            PageResult<Student> pageResult= studentService.page(studentQueryParam);
            return Result.success(pageResult);

        }

    //4.2 删除学员
    @DeleteMapping("{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工:"+ids);
        studentService.delete(ids);
        return Result.success();
    }

    //4.3 新增学员
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加员工:"+student);
        studentService.add(student);
        return Result.success();
    }

    //4.4 根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询员工:"+id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    //4.5 修改学员
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改员工:"+student);
        studentService.updata(student);
        return Result.success();
    }

    //4.6 违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id,@PathVariable Integer score){
        studentService.violationHandle(id,score);
        return Result.success();
    }
}
