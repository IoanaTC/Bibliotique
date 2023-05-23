package model;

import java.util.ArrayList;

// shelf is a singleton class but for each user in particular that owns a shelf
public class Shelf extends User{
    private static Shelf instance;
    private ArrayList<Book> favourites;
    private ArrayList<Book> wishlist;
    private ArrayList<Book> reading;
    private Shelf(){
        favourites = new ArrayList<Book>();
        wishlist = new ArrayList<Book>();
        reading = new ArrayList<Book>();
    }
    protected static Shelf buildShelf(){
        if(instance == null)
            instance =  new Shelf();
        return instance;
    }

    public ArrayList<Book> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Book> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Book> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<Book> wishlist) {
        this.wishlist = wishlist;
    }

    public ArrayList<Book> getReading() {
        return reading;
    }

    public void setReading(ArrayList<Book> reading) {
        this.reading = reading;
    }
}
