package com.springcache.service;


import com.springcache.Entity.StudentInfo;
import com.springcache.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
 * Created by Tiger on 2018/10/8.
 */
@Service
public class StudentInfoService {
 
    @Autowired
    StudentInfoMapper studentInfoMapper;
 
    /**
     * 保存学生信息
     * @param studentInfo
     * */
    public void saveStudentInfo(StudentInfo studentInfo){
        studentInfoMapper.saveStudentInfo(studentInfo);
    }
 
    /**
     * 根据学号查学生信息
     * @param studentId
     * @return
     * */
    public StudentInfo findByStudentId(Long studentId){
        return studentInfoMapper.findByStudentId(studentId);
    }
 
    /**
     * 根据学号更新家庭地址
     * @param studentId
     * @param famillyAddress
     * */
    public void updateFamillyAddress(Long studentId,String famillyAddress){
        studentInfoMapper.updateFamillyAddress(studentId,famillyAddress);
    }
}