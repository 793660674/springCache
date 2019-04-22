package com.springcache.Entity;

import lombok.AllArgsConstructor;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  import java.io.Serializable;
  import java.util.Date;

  /**
  * Created by Tiger on 2018/10/8.
  */
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class StudentInfo implements Serializable{

  private static final long serialVersionUID = 2597547944454691103L;

  private Long id;
  private Long studentId;
  private String name;
  private Integer age;
  private String famillyAddress;
  private Date createdDate;
  private Date updatedDate;


  }