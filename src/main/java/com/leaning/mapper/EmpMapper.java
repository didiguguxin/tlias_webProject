package com.leaning.mapper;

import com.leaning.pojo.Emp;
import com.leaning.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Mapper
public interface EmpMapper {
/*
---------------------------------------原始分页查询实现----------
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

    @Options(useGeneratedKeys = true, keyProperty = "id")// 设置主键回填
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    void deleteById(List<Integer> ids);


    Emp getById(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    @Select("select id,name from emp")
    List<Emp> listAll();

    @Select("select count(*) from emp where dept_id = #{deptId}")
    Long countEmpByDeptId(Integer deptId);
}


