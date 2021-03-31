package com.breadTravel.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class WorksJson {

    public JSONArray getPreviewJson(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("headImg", resultSet.getString("headImg"));
                jsonObject.put("nickName", resultSet.getString("nickName"));
                jsonObject.put("worksId", resultSet.getString("worksId"));
                jsonObject.put("userName", resultSet.getString("userName"));
                jsonObject.put("title", resultSet.getString("title"));
                jsonObject.put("keyWords", resultSet.getString("keyWords"));
                jsonObject.put("date", resultSet.getString("date"));
                jsonObject.put("day", resultSet.getLong("day"));
                jsonObject.put("skim", resultSet.getLong("skim"));
                jsonObject.put("region", resultSet.getString("region"));
                jsonObject.put("coverImg", resultSet.getString("coverImg"));
                jsonObject.put("praise", resultSet.getLong("praise"));
                jsonArray.put(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public JSONArray getContentJson(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("worksId", resultSet.getLong("worksId"));
                jsonObject.put("date", resultSet.getString("date"));
                jsonObject.put("time", resultSet.getString("time"));
                jsonObject.put("location", resultSet.getString("location"));
                jsonObject.put("photo", resultSet.getString("photo"));
                jsonObject.put("contentText", resultSet.getString("contentText"));
                jsonArray.put(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
