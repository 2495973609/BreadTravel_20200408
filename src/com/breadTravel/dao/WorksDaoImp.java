package com.breadTravel.dao;

import com.breadTravel.entity.WorksContent;
import com.breadTravel.entity.WorksPreview;
import com.breadTravel.util.DBUtil;
import com.breadTravel.util.WorksJson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WorksDaoImp implements WorksDao {
    private static Connection connection = new DBUtil().getConnection_kbt();
    @Override
    public JSONArray getRecommendPreview() {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select a.headImg,a.nickName,b.*  from T_UserInfo a,T_WorksPreview b where a.userName=b.userName";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getPreviewJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAttentionPreview(String userName) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select a.headImg,a.nickName,c.*  from T_UserInfo a,T_Attention b,T_WorksPreview c where" +
                        " b.userName=? and a.userName=b.attentionUser and c.userName=a.userName";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getPreviewJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getPraisePreview(String userName) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select a.headImg,a.nickName,c.*  from T_UserInfo a,T_Praise b,T_WorksPreview c where" +
                        " b.userName=? and c.worksId=b.worksId and a.userName=c.userName";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getPreviewJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getMyWorksPreview(String userName) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select a.headImg, a.nickName, b.* from T_UserInfo a,T_WorksPreview b where " +
                        "a.userName=? and b.userName=a.userName";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getPreviewJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getWorksContent(long worksId) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select * from T_WorksContent where worksId=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, worksId);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getContentJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public String addBrowse(long worksId,long skimNum,String userName,String attentionUser) {
        String result;
        JSONObject jsonObject=new JSONObject();
        try{
            if (connection!=null){
                String sql="update T_WorksPreview set skim=? where worksId=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setLong(1,skimNum);
                preparedStatement.setLong(2,worksId);
                preparedStatement.executeUpdate();
                String sql_praise="select * from T_Praise where userName=? and worksId=?";
                preparedStatement=connection.prepareStatement(sql_praise);
                preparedStatement.setString(1,userName);
                preparedStatement.setLong(2,worksId);
                ResultSet resultSet=preparedStatement.executeQuery();

                if (resultSet.next()){
                    jsonObject.put("isPraise","T");
                }else {
                    jsonObject.put("isPraise","F");
                }
                String sql_attention="select * from T_Attention where userName=? and attentionUser=?";
                preparedStatement=connection.prepareStatement(sql_attention);
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,attentionUser);
                resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    jsonObject.put("isAttention","T");
                }else {
                    jsonObject.put("isAttention","F");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public long addWorksPreview(WorksPreview worksPreview) {
        long wordsId=0;
        try{
            if (connection!=null){
                String sql="insert into T_WorksPreview values (?, ?, ?, ?, ?, default , ?, ?, default , null, null) select @@IDENTITY as T_WorksPreview";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,worksPreview.getUserName());
                preparedStatement.setString(2,worksPreview.getTitle());
                preparedStatement.setString(3,"#测试");
                preparedStatement.setString(4,worksPreview.getDate());
                preparedStatement.setLong(5,worksPreview.getDay());
                preparedStatement.setString(6,worksPreview.getRegion());
                preparedStatement.setString(7,worksPreview.getCoverImg());
                if (preparedStatement.executeUpdate()>0){
                    ResultSet resultSet=preparedStatement.getGeneratedKeys();
                    if (resultSet.next()){
                        wordsId=resultSet.getLong(1);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return wordsId;
    }

    @Override
    public boolean addWorksContent(WorksContent worksContent) {
        boolean flag=false;
        try {
            if (connection!=null){
                String sql="insert into T_WorksContent values (?,?,?,?,?,?,null,null)";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setLong(1,worksContent.getWorksId());
                preparedStatement.setString(2,worksContent.getDate());
                preparedStatement.setString(3,worksContent.getTime());
                preparedStatement.setString(4,worksContent.getLocation());
                preparedStatement.setString(5,worksContent.getPhoto());
                preparedStatement.setString(6,worksContent.getContentText());
                if (preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteWorks(long worksId) {
        boolean flag=false;
        try{
            if (connection!=null){
                String sql_content="delete  from T_WorksContent where worksId=?";
                String sql_preview="delete  from T_WorksPreview where worksId=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql_content);
                preparedStatement.setLong(1,worksId);
                if (preparedStatement.executeUpdate()>=0){
                    preparedStatement=connection.prepareStatement(sql_preview);
                    preparedStatement.setLong(1,worksId);
                    if (preparedStatement.executeUpdate()>0){
                        flag=true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public void praise(String userName, long worksId, String praise,long praiseNum) {
        try{
            if (connection!=null){
                String sql_praise;
                String sql_preview;
                if (praise.equals("del")){
                    sql_praise="delete from T_Praise where userName=? and worksId=?";
                    sql_preview="update T_WorksPreview set praise="+(praiseNum-1)+" where worksId="+worksId;
                }else {
                    sql_praise="insert into T_Praise values(?,?,null,null)";
                    sql_preview="update T_WorksPreview set praise="+(praiseNum+1)+" where worksId="+worksId;
                }
                PreparedStatement preparedStatement=connection.prepareStatement(sql_praise);
                preparedStatement.setString(1,userName);
                preparedStatement.setLong(2,worksId);
                preparedStatement.executeUpdate();
                preparedStatement=connection.prepareStatement(sql_preview);
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void attention(String userName, String worksUser, String attention) {
        try{
            if (connection!=null){
                String sql;
                if (attention.equals("del")){
                    sql="delete from T_Attention where userName=? and attentionUser=?";
                }else {
                    sql="insert into T_Attention values(?,?,null,null)";
                }
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,worksUser);
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JSONArray getCityPreview(String city) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (connection != null) {
                String sql = "select a.headImg,a.nickName,b.*  from T_UserInfo a,T_WorksPreview b where b.region like '%"+city+"%' and a.userName=b.userName";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                jsonArray = new WorksJson().getPreviewJson(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getSearchPreview(String city, String point) {
        return null;
    }
}
