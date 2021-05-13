package com.example.educare;

public class MyListData {

    private String subject;
    private String facultyName;
    private String time;
    private  String dayId;
    private int imgId;
    public MyListData(String subject, String facultyName, String time, int imgId,String dayId) {
        this.subject = subject;
        this.facultyName = facultyName;
        this.time = time;
        this.imgId = imgId;
        this.dayId = dayId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getfacultyName() {
        return facultyName;
    }
    public void setfacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    public String gettime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDayId(){
        return dayId;
    }


}
