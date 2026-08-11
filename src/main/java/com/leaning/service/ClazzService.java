package com.leaning.service;

import com.leaning.pojo.Clazz;
import com.leaning.pojo.ClazzQueryParam;
import com.leaning.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz findById(Integer id);

    void updata(Clazz clazz);

    List<Clazz> list();
}

