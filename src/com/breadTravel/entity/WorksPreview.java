package com.breadTravel.entity;

public class WorksPreview {
    private long worksId;
    private String userName;
    private String title;
    private String keyWords;
    private String date;
    private long day;
    private long skim;
    private String region;
    private String headImg;
    private String nickName;
    private String coverImg;
    private long praise;


    public WorksPreview() {

    }

    public WorksPreview(long worksId, String userName, String title, String keyWords, String date, long day, long skim, String region, String headImg, String nickName, String coverImg, long praise) {
        this.worksId = worksId;
        this.userName = userName;
        this.title = title;
        this.keyWords = keyWords;
        this.date = date;
        this.day = day;
        this.skim = skim;
        this.region = region;
        this.headImg = headImg;
        this.nickName = nickName;
        this.coverImg = coverImg;
        this.praise = praise;
    }

    public long getWorksId() {
        return worksId;
    }

    public void setWorksId(long worksId) {
        this.worksId = worksId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public long getSkim() {
        return skim;
    }

    public void setSkim(long skim) {
        this.skim = skim;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public long getPraise() {
        return praise;
    }

    public void setPraise(long praise) {
        this.praise = praise;
    }
}
