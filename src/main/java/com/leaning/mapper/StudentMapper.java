package com.leaning.mapper;

import com.leaning.pojo.Dept;
import com.leaning.pojo.Student;
import com.leaning.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);

    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address," +
            " degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, " +
            "#{degree}, #{graduationDate}, #{clazzId}, IFNULL(#{violationCount},0), " +
            "IFNULL(#{violationScore},0), #{createTime}, #{updateTime})")
    void add(Student student);

    @Select("select * from student where id=#{id}")
    Student findById(Integer id);

    @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},id_card=#{idCard}," +
            "is_college=#{isCollege},address=#{address},degree=#{degree},graduation_date=#{graduationDate}," +
            "clazz_id=#{clazzId}," +
            "violation_count=IFNULL(#{violationCount},0)," +
            "violation_score=IFNULL(#{violationScore},0)," +
            "update_time=#{updateTime} " +
            "where id=#{id}")
    void updata(Student student);

    List<Map<String, Object>> countStudentByClazz();

    List<Map<String, Object>> countStudentByDegree();
}
