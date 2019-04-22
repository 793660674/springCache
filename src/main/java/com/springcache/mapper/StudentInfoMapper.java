package com.springcache.mapper;

import com.springcache.Entity.StudentInfo;
import org.apache.ibatis.annotations.*;


/**
 * Created by Tiger on 2018/10/8.
 */
@Mapper
public interface StudentInfoMapper {
    @Insert("insert into student_info(student_id,name,age,familly_address)" +
            " values(#{studentId},#{name},#{age},#{famillyAddress})")
    /**
     * 通过bean保存实体类是，建议不要通过@Param注解，负责实体类的属性都在@Param中找
     * */
    void saveStudentInfo(StudentInfo studentInfo);


    @Select("select * from student_info where student_id = #{studentId}")
    StudentInfo findByStudentId(@Param("studentId") Long studentId);


    @Update("update student_info set familly_address = #{famillyAddress},updated_date = now() ")
    void updateFamillyAddress(@Param("studentId") Long studentId,@Param("famillyAddress") String famillyAddress);
}