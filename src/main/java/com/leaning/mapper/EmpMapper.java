package com.leaning.mapper;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface EmpMapper {
/*
---------------------------------------原始分页查询实现---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //返回查询总条数
    @Select("select count(*)from emp left join dept on emp.dept_id = dept.id")
    public long count();

    //查询所有的员工及其对应的部门名称
    @Select("select emp.*,dept.name from emp left join dept on emp.dept_id = dept.id " +
            "order by emp.update_time desc limit #{start},#{pagesSize}")
    public List<Emp> list(Integer start, Integer pagesSize);


 */
    /*
        @Select("select emp.*,dept.name from emp left join dept on emp.dept_id = dept.id " +
                "order by emp.update_time desc  ")
        public List<Emp> list();

     */
     /*   @Select("select emp.* ,dept.name\nfrom emp left join dept\n" +
                "    on emp.dept_id = dept.id\n" +
                "                      where emp.name like '%阮%'\n" +
                "                      and emp.gender = 1\n" +
                "                      and emp.entry_date between '2010-01-01' and '2020-01-01'\n" +
                "                      order by emp.update_time desc")

      */
        public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> list(EmpQueryParam empQueryParam);
}


