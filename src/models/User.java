package models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String name;
    private int age;
    private Company company;

    //there is a single shelf per user -> singleton
    private boolean has_shelf = false;
    private Shelf shelf;
    private ArrayList<BookClub> bookClubs;
    private ArrayList<Review> reviews;

    public User() {
        name = "Unknown User";
        age = -1;
        company = null;
        bookClubs = new ArrayList<>();
        reviews = new ArrayList<>();
    }
    // shelf building is common to all constructors
    {
        if(this.has_shelf == false){
            shelf = Shelf.buildShelf();
            has_shelf = true;
        }
        else System.out.println("This user already has a shelf assigned");
    }
}
