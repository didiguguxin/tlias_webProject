package com.leaning.mapper;

import com.leaning.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time,update_time from dept order by update_time desc ")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Update("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept findById(Integer id) ;

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updata(Dept dept);
}
