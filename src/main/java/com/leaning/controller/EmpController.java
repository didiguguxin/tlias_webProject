package com.leaning.controller;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import com.leaning.pojo.PageResult;
import com.leaning.pojo.Result;
import com.leaning.service.EmpService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    @GetMapping

    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
                       ) {
        log.info("查询"+page+pageSize+name+ gender+begin+end+"员工数据");
        PageResult<Emp> pageResult=empService.page(page,pageSize,name, gender, begin, end);
        return Result.success(pageResult);

   }

     */
    //查询员工
    @GetMapping

    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询"+empQueryParam+"员工数据");
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);

    }

    //添加员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工:"+emp);
        empService.add(emp);
        return Result.success();
    }

    //删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工:"+ids);
        empService.delete(ids);
        return Result.success();
    }

    //-------员工修改
    //查询员工
    @GetMapping("/{id}")
    public Result update(@PathVariable Integer id) {
        log.info("员工查询:"+id);
        Emp emp=empService.getById(id);
        return Result.success(emp);
    }
    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("员工修改:"+emp);
        empService.update(emp);
        return Result.success();
    }

}
