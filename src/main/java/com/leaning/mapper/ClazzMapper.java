package com.leaning.mapper;

import com.leaning.pojo.Clazz;
import com.leaning.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into clazz(name,room,begin_date,end_date," +
            "master_id,subject,create_time,update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate}," +
            "#{masterId},#{subject},now(),now())")
    void add(Clazz clazz);

    @Select("select id,name,room,begin_date,end_date," +
            "master_id,subject,create_time,update_time " +
            "from clazz where id=#{id}")
    Clazz findById(Integer id);

    @Update("update clazz set name=#{name},room=#{room}," +
            "begin_date=#{beginDate},end_date=#{endDate}," +
            "master_id=#{masterId},subject=#{subject}," +
            "update_time=now() where id=#{id}")
    void updata(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> listAll();
}
