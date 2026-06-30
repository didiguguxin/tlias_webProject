package com.leaning.controller;

import com.leaning.pojo.Emp;
import com.leaning.pojo.PageResult;
import com.leaning.pojo.Result;
import com.leaning.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(Integer page, Integer pageSize) {
        log.info("查询"+page+pageSize+"全部员工数据");
        PageResult<Emp> pageResult=empService.page(page,pageSize);
        return Result.success(pageResult);

    }

}
