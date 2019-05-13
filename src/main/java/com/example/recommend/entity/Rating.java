package com.example.recommend.entity;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue
    private Integer userId;
    private Integer spotId;
    private Integer rating; //喜爱程度,点评率
    private Integer rateTime;//点评次数
}
