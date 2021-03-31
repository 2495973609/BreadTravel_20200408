package com.breadTravel.entity;

public class WorksContent {
    private long worksId;
    private String date;
    private String time;
    private String location;
    private String photo;
    private String contentText;

    public WorksContent() {

    }

    public WorksContent(long worksId, String date, String time, String location, String photo, String contentText) {
        this.worksId = worksId;
        this.date = date;
        this.time = time;
        this.location = location;
        this.photo = photo;
        this.contentText = contentText;
    }

    public long getWorksId() {
        return worksId;
    }

    public void setWorksId(long worksId) {
        this.worksId = worksId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
