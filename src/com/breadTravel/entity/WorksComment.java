package com.breadTravel.entity;

public class WorksComment {
    private long worksId;
    private String sendUser;
    private String receiveUser;
    private String time;
    private String CommentText;
    private String  nickName;
    private String headImg;
    public WorksComment() {

    }

    public WorksComment(long worksId, String sendUser, String receiveUser, String time, String commentText) {
        this.worksId = worksId;
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.time = time;
        CommentText = commentText;
    }

    public long getWorksId() {
        return worksId;
    }

    public void setWorksId(long worksId) {
        this.worksId = worksId;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommentText() {
        return CommentText;
    }

    public void setCommentText(String commentText) {
        CommentText = commentText;
    }
}
