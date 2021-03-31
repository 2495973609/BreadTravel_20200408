package com.breadTravel.dao;

import com.breadTravel.entity.WorksContent;
import com.breadTravel.entity.WorksPreview;
import org.json.JSONArray;

import java.util.List;

public interface WorksDao {
    JSONArray getRecommendPreview();
    JSONArray getAttentionPreview(String userName);
    JSONArray getPraisePreview(String userName);
    JSONArray getMyWorksPreview(String userName);
    JSONArray getWorksContent(long worksId);
    String addBrowse(long worksId,long skimNum,String userName,String attention);
    long addWorksPreview(WorksPreview worksPreview);
    boolean addWorksContent(WorksContent worksContent);
    boolean deleteWorks(long worksId);
    void praise(String userName,long worksId,String praise,long praiseNum);
    void attention(String userName,String worksUser,String attention);
    JSONArray getCityPreview(String city);
    JSONArray getSearchPreview(String city,String point);
}
