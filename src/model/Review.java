package model;

import java.util.Date;

public class Review {
    private String title;
    private String content;
    private Date date;

    public Review() {
        this.title = "No title chosen for this review";
        this.content = "There is no content t display";
        this.date = new Date();
    }

    public Review(String title, String content, Date date, String booktitle, String author) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Review(String title, String content, String booktitle, String author) {
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
