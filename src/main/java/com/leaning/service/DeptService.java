package com.leaning.service;

import com.leaning.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void updata(Dept dept);
}
