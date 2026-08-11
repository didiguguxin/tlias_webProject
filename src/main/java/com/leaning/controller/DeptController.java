package com.leaning.controller;

import com.leaning.pojo.Dept;
import com.leaning.pojo.Result;
import com.leaning.service.DeptService;
import com.leaning.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 日志
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询全部部门数据
    //@RequestMapping(value = "/depts",method= RequestMethod.GET)
   // @GetMapping("/depts")
    @GetMapping
    public Result list() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList =deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    //@DeleteMapping("/depts")
    @DeleteMapping
    public Result delete(Integer id){
        log.info("删除部门ID:"+ id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门
   // @PostMapping("/depts")
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加部门:"+dept);
        log.info("添加部门:"+dept);
        deptService.add(dept);
        return Result.success();
    }

    //查询部门
    //@GetMapping("/depts/{id}")
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        //System.out.println("查询"+id+"部门数据");
        log.info("查询"+id+"部门数据");
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    //修改部门
   // @PutMapping("/depts")
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门:"+dept);
        log.info("修改部门:"+dept);
        deptService.updata(dept);
        return Result.success();
    }

}
