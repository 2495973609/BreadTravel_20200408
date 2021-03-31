package com.breadTravel.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class CommentJson {

    public JSONArray getCommentJson(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sendUser", resultSet.getString("sendUser"));
                jsonObject.put("receiveUser", resultSet.getString("receiveUser"));
                jsonObject.put("time", resultSet.getString("time"));
                jsonObject.put("CommentText", resultSet.getString("CommentText"));
                jsonObject.put("nickName",resultSet.getString("nickName"));
                jsonObject.put("headImg",resultSet.getString("headImg"));
                jsonArray.put(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
