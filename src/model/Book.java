package model;

import java.util.Date;

public abstract class Book {
    private String title;
    private String author;
    private String description;
    private Date published;
    // this attribute verifies if the current book is available
    // or is currently in the possession of other user
    private Boolean available;

    public Book() {
        title = "Unknown title";
        author = "Unknown author";
        description = "None";
    }
    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
        description = "No description available for this book";
    }

    public Book(String title, String author, String description, Date published) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    {
        available = true;
    }
}
