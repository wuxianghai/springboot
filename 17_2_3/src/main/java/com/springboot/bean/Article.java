package com.springboot.bean;

import java.util.Date;

/**
 * Created by Hardy.wu on 2017/2/8.
 */
public class Article {

    private long id ;

    private String title;

    private String type;

    private String lable;

    private Date addTime;

    private boolean deleteStuatus;

    private long click_number;

    private long press_id;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public boolean isDeleteStuatus() {
        return deleteStuatus;
    }

    public void setDeleteStuatus(boolean deleteStuatus) {
        this.deleteStuatus = deleteStuatus;
    }

    public long getClick_number() {
        return click_number;
    }

    public void setClick_number(long click_number) {
        this.click_number = click_number;
    }

    public long getPress_id() {
        return press_id;
    }

    public void setPress_id(long press_id) {
        this.press_id = press_id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", lable='" + lable + '\'' +
                ", addTime=" + addTime +
                ", deleteStuatus=" + deleteStuatus +
                ", click_number=" + click_number +
                ", press_id=" + press_id +
                '}';
    }
}
