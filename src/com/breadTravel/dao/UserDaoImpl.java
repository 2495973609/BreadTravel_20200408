package com.breadTravel.dao;

import com.breadTravel.entity.UserInfo;
import com.breadTravel.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    private static Connection connection=new DBUtil().getConnection_kbt();
    @Override
    public List<UserInfo> queryLogin(String account, String passWord) {
        List<UserInfo> list=new ArrayList<>();
        try {
            if (connection!=null){
                String sql="select * from T_UserInfo where userName=? and passWord=? or email=? and passWord=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,account);
                preparedStatement.setString(2,passWord);
                preparedStatement.setString(3,account);
                preparedStatement.setString(4,passWord);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    list.add(new UserInfo(resultSet.getString("userName"), resultSet.getString("email")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean queryRegister(String userName, String passWord,String email) {
        boolean flag=false;
        try {
            if(connection!=null){
                String sql="insert into T_UserInfo values(?,?,?,?,?)";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,passWord);
                preparedStatement.setString(3,email);
                preparedStatement.setString(4,"logo.jpg");
                preparedStatement.setString(5,null);
                if(preparedStatement.executeUpdate()    >0){
                    flag=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean queryCheckEmail(String email) {
        boolean flag=false;
        try {
            if(connection!=null){
                String sql="select * from T_UserInfo where email=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,email);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    flag=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean uploadHeadImg(String userName,String filePath) {
        boolean flag=false;
        try {
            if(connection!=null){
                String sql="update T_UserInfo set headImg=? where email=? or userName=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,filePath);
                preparedStatement.setString(2,userName);
                preparedStatement.setString(3,userName);
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public String querySelectHeadImg(String userName) {
        String headImg="";
        try {
            if(connection!=null){
                String sql="select headImg from T_UserInfo where userName=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    headImg=resultSet.getString("headImg");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return headImg;
    }

    @Override
    public String querySelectNickName(String userName) {
        String nickName="";
        try {
            if(connection!=null){
                String sql="select nickName  from T_UserInfo where userName=?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    nickName=resultSet.getString("nickName");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nickName;
    }

}
