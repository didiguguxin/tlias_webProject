package com.leaning.controller;

import com.leaning.pojo.Emp;
import com.leaning.pojo.LoginInfo;
import com.leaning.pojo.Result;
import com.leaning.service.EmpService;
import com.leaning.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录请求:{}", emp);
        LoginInfo loginInfo = empService.login(emp);

        if(loginInfo == null) {
            return Result.error("用户名或密码错误");
        }

        return Result.success(loginInfo);

    }




}