package models;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private ArrayList<Book> favourites;
    private ArrayList<Book> wishlist;
    private ArrayList<Book> currentlyReading;
    private Shelf(){
        favourites = new ArrayList<Book>();
        wishlist = new ArrayList<Book>();
        currentlyReading = new ArrayList<Book>();
    }
    public static Shelf buildShelf(){
        Shelf shelf = new Shelf();
        return shelf;
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
