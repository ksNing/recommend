package com.example.recommend.mapper;

import com.example.recommend.pojo.Spot;

import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface SpotMapper {


    int deleteByPrimaryKey(Integer spotId);

    int insert(Spot record);

    int insertSelective(Spot record);


    Spot selectByPrimaryKey(Integer spotId);


    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);

    /*
    查看某一特定用户下的所有景点实体
     */
    List<Spot> querySpotBySql(Integer userId);

    List<Spot> querySpotByIds(List ids);
}