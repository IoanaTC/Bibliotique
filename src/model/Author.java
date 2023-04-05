package model;

import java.util.ArrayList;

public class Author {
    private String name;
    private int age;
    private String information;
    private ArrayList<Book> books;

    public Author() {
        name = "Unknown author";
        age = -1;
        information = "No information found about this author";
    }

    public Author(String name) {
        this.name = name;
        this.age = age;
        information = "No information found about this author";
    }

    public Author(String name, int age, String information) {
        this.name = name;
        this.age = age;
        this.information = information;
    }

    {
        books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
