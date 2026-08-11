package com.leaning.controller;

import com.leaning.pojo.*;
import com.leaning.pojo.Clazz;
import com.leaning.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 日志
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //3.1 班级列表查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询"+ clazzQueryParam +"班级列表");
        PageResult<Clazz> pageResult= clazzService.page(clazzQueryParam);
        return Result.success(pageResult);

    }

    //3.2 删除班级
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级ID:"+ id);
        clazzService.deleteById(id);
        return Result.success();
    }

    //3.3 添加班级
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级:"+clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    //3.4 根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询"+id+"班级数据");
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    //3.5 修改班级
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级:"+clazz);
        clazzService.updata(clazz);
        return Result.success();
    }

    //3.6 查询所有班级
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级数据");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}
