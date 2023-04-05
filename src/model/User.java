package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstname;
    private String surname;
    private int age;
    private Company company;

    //there is a single shelf per user -> singleton
    private Shelf shelf;
    private ArrayList<BookClub> bookClubs;
    private Map<String, Review> reviews;

    public Shelf getShelf(){
        if(shelf == null){
            shelf = Shelf.buildShelf();
        }
        return shelf;
    }
    public User() {
        firstname = "Unknown";
        surname = "User";
        age = -1;
        company = null;
        bookClubs = new ArrayList<>();
        reviews = new HashMap<String, Review>();
    }
    public User(String firstname, String surname, int age, Company company,
                Shelf shelf, ArrayList<BookClub> bookClubs, Map<String, Review> reviews) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.company = company;
        this.shelf = shelf;
        this.bookClubs = bookClubs;
        this.reviews = reviews;
    }
    public User(String firstname, String surname, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }
    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public ArrayList<BookClub> getBookClubs() {
        return bookClubs;
    }

    public void setBookClubs(ArrayList<BookClub> bookClubs) {
        this.bookClubs = bookClubs;
    }

    public Map<String, Review> getReviews() {
        return reviews;
    }

    public void setReviews(Map<String, Review> reviews) {
        this.reviews = reviews;
    }
}
