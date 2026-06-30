package com.leaning.mapper;

import com.leaning.pojo.Dept;
import com.leaning.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.swing.*;
import java.util.List;


@Mapper
public interface EmpMapper {

    //返回查询总条数
    @Select("select count(*)from emp left join dept on emp.dept_id = dept.id")
    public long count();

    //查询所有的员工及其对应的部门名称
    @Select("select emp.*,dept.name from emp left join dept on emp.dept_id = dept.id " +
            "order by emp.update_time desc limit #{start},#{pagesSize}")
    public List<Emp> list(Integer start, Integer pagesSize);

}
