package com.breadTravel.dao;

import com.breadTravel.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<UserInfo> queryLogin(String userName, String passWord);
    public boolean queryRegister(String userName, String passWord, String email);
    public boolean queryCheckEmail(String email);
    public boolean uploadHeadImg(String userName,String filePath);
    public String querySelectHeadImg(String userName);
    public String querySelectNickName(String userName);
}
