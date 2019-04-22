package com.springcache.controller;

import com.springcache.Entity.StudentInfo;
import com.springcache.service.StudentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * Created by Tiger on 2018/10/8.
 */
@RestController
@RequestMapping("/student")
@CacheConfig(cacheNames = "studentInfo")
@Slf4j
public class StudentInofController {
    @Autowired
    StudentInfoService studentInfoService;
 
    /**
     * 保存学生信息
     * @param studentId
     * @param name
     * @param age
     * @param famillyAddress
     * */
    @PostMapping("/save")
    public void saveStudentInfo(@RequestParam("student_id") Long studentId,
                                @RequestParam("name") String name,
                                @RequestParam("age") Integer age,
                                @RequestParam("familly_address") String famillyAddress){
        StudentInfo studentInfo = StudentInfo.builder()
                .studentId(studentId)
                .name(name)
                .age(age)
                .famillyAddress(famillyAddress)
                .build();
        studentInfoService.saveStudentInfo(studentInfo);
    }
 
    /**
     * 根据学号查学生信息
     * @param studentId
     * @return
     * */
    @PostMapping("/findByStudentId")
    @Cacheable(key = "#studentId")
    public StudentInfo findByStudentId(@RequestParam("student_id") Long studentId){
        log.info("Get student information based on student number:{}",studentId);
        System.out.println("查询信息>>>"+studentId);
        return studentInfoService.findByStudentId(studentId);
    }
 
    @PostMapping("/updateFamillyAddress")
    //删除对应key的缓存
    @CacheEvict(key = "#studentId")
    public void updateFamillyAddress(@RequestParam("student_id") Long studentId,
                                     @RequestParam("familly_address") String famillyAddress){
        studentInfoService.updateFamillyAddress(studentId,famillyAddress);
    }
 
}