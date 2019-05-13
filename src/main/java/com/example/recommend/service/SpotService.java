package com.example.recommend.service;

import com.example.recommend.pojo.Spot;

import java.util.List;

public interface SpotService {
    List<Spot> queryLookedSpotByUser(int userId);
    /*
    基于用户相似度的推荐
     */
    List<Spot> recommendSpotBasedUser(int userId,int size);
    /*
    基于物品相似度的推荐
     */
    List<Spot> recommendSpotBasedItem(int userId,int size);
}
