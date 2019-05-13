package com.example.recommend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String touxiangId;

    private String spotName;//喜欢的景点
    //private String loveCity;
    private String userName;
    private String password;
    //private List<String> spotNameList;
}