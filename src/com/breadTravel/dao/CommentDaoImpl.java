package com.breadTravel.dao;

import com.breadTravel.util.CommentJson;
import com.breadTravel.util.DBUtil;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentDaoImpl implements CommentDao {
    private static Connection connection = new DBUtil().getConnection_kbt();
    @Override
    public JSONArray queryComment(long WorksId) {
        JSONArray jsonArray=new JSONArray();
       try{
           if (connection!=null){
               String sql="select a.*,b.nickName,b.headImg from T_WorksComment a,T_UserInfo b where a.worksId=? and b.userName=a.sendUser";
               PreparedStatement preparedStatement=connection.prepareStatement(sql);
               preparedStatement.setLong(1,WorksId);
               ResultSet resultSet=preparedStatement.executeQuery();
               jsonArray=new CommentJson().getCommentJson(resultSet);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return jsonArray;
    }

    @Override
    public boolean addComment(long WorksId, String sendUser, String receiveUser, String time, String CommentText) {
        boolean flag=false;
        try {
            if (connection!=null){
                String sql="insert into T_WorksComment values (?,?,?,?,?,null,null)";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setLong(1,WorksId);
                preparedStatement.setString(2,sendUser);
                preparedStatement.setString(3,receiveUser);
                preparedStatement.setString(4,time);
                preparedStatement.setString(5,CommentText);
                if (preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
