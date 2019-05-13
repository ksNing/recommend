package com.example.recommend.dao;

import com.example.recommend.pojo.SpotRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRecommendRepository extends JpaRepository<SpotRecommend,String> {
     SpotRecommend findBySpotName(String spotName);
}
