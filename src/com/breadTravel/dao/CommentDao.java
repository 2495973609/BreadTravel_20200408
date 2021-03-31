package com.breadTravel.dao;

import org.json.JSONArray;

public interface CommentDao {
    JSONArray queryComment(long WorksId);
    boolean addComment(long WorksId,String sendUser,String receiveUser,String time,String CommentText);
}
