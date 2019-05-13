package com.example.recommend.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpotRecommender {
    //相邻周围的个数
    final private int NEIGHBORHOOD_NUM = 10;
    @Resource(name = "fileDataModel")
    private DataModel dataModel;

    /*
    利用mahout开源库得到了大量的推荐商品（景点）
     */
    private List<Long> getRecommendedItemIDs(List<RecommendedItem> recommendedItemList) {
        List<Long> recommendItems = new ArrayList<>();
        for(int i = 0 ; i < recommendedItemList.size();i ++) {
            RecommendedItem recommendedItem = recommendedItemList.get(i);
            recommendItems.add(recommendedItem.getItemID());
        }
        return recommendItems;
    }
    /*
    基于用户特点的景点推荐
     */
    public List<Long> userBasedRecommender(long userId,int size) throws TasteException {
        //利用mahout来建立用户相似矩阵
        UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
        //建立一个最近矩阵模型
        NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM,similarity,dataModel);
        Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(dataModel,neighborhood,similarity));
        List<RecommendedItem> recommendedItems = recommender.recommend(userId,size);
        return getRecommendedItemIDs(recommendedItems);
    }

    /*
    基于物品（景点）的推荐
     */
    public List<Long> itemBasedRecommender(long userId,int size) throws TasteException {
        List<Long> recommendItems = new ArrayList<>();
        //建立一个物品相似矩阵
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        List<RecommendedItem> recommendations = recommender.recommend(userId, size);
        return getRecommendedItemIDs(recommendations);
    }

}
