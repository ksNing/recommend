package com.example.recommend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Tupian {
    @Id
    private String spotId;
    private String spotName;
    private String spotCity;
    private String spotRank;
    private String picture;
    private String introduce;
    private String playTime;
    private String spotGrade;
    private String commentsNum;
}
