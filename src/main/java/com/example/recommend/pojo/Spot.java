package com.example.recommend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Spot {
    @Id
    @GeneratedValue
    private Integer id;
    private String spotId;
    private String spotName;
    private String spotCityId;
    private String spotRank;
    private String spotIntro;
    private String spotPlayTime;
    private String spotGrade;
    private String spotAddr;
    private String spotTel;
    private String spotOpenTime;
    private String spotTicket;
    private String spotVisitSeason;
    private String spotTrafficGuide;
    private String spotCommentsNum;
    private String spot5starNum;
    private String spot4starNum;
    private String spot3starNum;
    private String spot2starNum;
    private String spot1starNum;




}