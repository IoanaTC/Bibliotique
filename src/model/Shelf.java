package model;

import java.util.ArrayList;

public class Shelf extends User{
    private static Shelf instance;
    private ArrayList<Book> favourites;
    private ArrayList<Book> wishlist;
    private ArrayList<Book> currentlyReading;
    private Shelf(){
        favourites = new ArrayList<Book>();
        wishlist = new ArrayList<Book>();
        currentlyReading = new ArrayList<Book>();
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

    public ArrayList<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(ArrayList<Book> currentlyReading) {
        this.currentlyReading = currentlyReading;
    }
}