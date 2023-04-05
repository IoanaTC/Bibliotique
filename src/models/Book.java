package models;

import java.util.Date;

public abstract class Book {
    private String name;
    private Author author;
    private String description;
    private Date publishedon;
    private boolean available;

    public Book() {
        name = "Unknown name";
        author = null;
        description = null;
    }
    public Book(String name, Author author, Date publishedon) {
        this.name = name;
        this.author = author;
        this.publishedon = publishedon;
        description = "No description available for this book";
    }

    public Book(String name, Author author, String description, Date publishedon) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.publishedon = publishedon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedon() {
        return publishedon;
    }

    public void setPublishedon(Date publishedon) {
        this.publishedon = publishedon;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    {
        available = true;
    }
}
