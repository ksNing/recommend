package com.example.recommend.service.Impl;

import com.example.recommend.dao.SpotRepository;
import com.example.recommend.mapper.SpotMapper;
import com.example.recommend.mapper.UserMapper;
import com.example.recommend.pojo.Spot;
import com.example.recommend.recommend.SpotRecommender;
import com.example.recommend.service.SpotService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {
    @Autowired
    private SpotMapper spotMapper;
    @Autowired
    private SpotRecommender spotRecommender;

    @Override
    public List<Spot> queryLookedSpotByUser(int userId) {
        return spotMapper.querySpotBySql(userId);
    }

    @Override
    public List<Spot> recommendSpotBasedUser(int userId, int size) {
        List<Long> recommendedItems = null;
        try {
            recommendedItems = spotRecommender.userBasedRecommender(new Integer(userId).longValue(),size);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return spotMapper.querySpotByIds(recommendedItems);
    }

    @Override
    public List<Spot> recommendSpotBasedItem(int userId, int size) {
        List<Long> recommendedItems = null;
        try {
            recommendedItems = spotRecommender.itemBasedRecommender(new Integer(userId).longValue(),size);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return spotMapper.querySpotByIds(recommendedItems);
    }

}
