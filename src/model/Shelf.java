package model;

import config.DatabaseConfiguration;
import service.BookService;
import service.ClubService;
import service.CompanyService;
import service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// shelf is a singleton class but for each user in particular that owns a shelf
public class Shelf{
    private static Shelf instance;
    private final User user;
    private LinkedHashMap<String, ArrayList<Book>> favourites;
    private LinkedHashMap<String, ArrayList<Book>> wishlist;
    private LinkedHashMap<String, ArrayList<Book>> reading;
    private Shelf(User user){
        this.user = user;

        // getting the books from each category using the associative tables
        favourites = dbGetShelf("favourites");
        wishlist = dbGetShelf("wishlist");
        reading = dbGetShelf("reading");
    }
    private LinkedHashMap<String, ArrayList<Book>> dbGetShelf(String tableName){
        LinkedHashMap<String, ArrayList<Book>> shelf = new LinkedHashMap<>();
        ArrayList<Book> fiction = new ArrayList<Book>();
        ArrayList<Book> graphic = new ArrayList<Book>();
        ArrayList<Book> education = new ArrayList<Book>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT fiction_id, graphic_id, education_id FROM " + tableName + " WHERE user_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, UserService.getUserId(user));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int fiction_id = resultSet.getInt(1);
                int graphic_id = resultSet.getInt(2);
                int education_id = resultSet.getInt(3);

                if(fiction_id != 0){
                    Book book = BookService.getFictionById(fiction_id);
                    fiction.add(book);
                } else if(graphic_id != 0){
                    Book book = BookService.getGraphicById(graphic_id);
                    graphic.add(book);
                } else if(education_id != 0){
                    Book book = BookService.getEducationById(education_id);
                    education.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shelf.put("fiction", fiction);
        shelf.put("graphic", graphic);
        shelf.put("education", education);
        return shelf;
    }
    public static Shelf buildShelf(User user){
        return new Shelf(user);
    }

    public LinkedHashMap<String, ArrayList<Book>> getFavourites() {
        return favourites;
    }
    public void setFavourites(LinkedHashMap<String, ArrayList<Book>> favourites) {
        this.favourites = favourites;
    }
    public LinkedHashMap<String, ArrayList<Book>> getWishlist() {
        return wishlist;
    }
    public void setWishlist(LinkedHashMap<String, ArrayList<Book>> wishlist) {
        this.wishlist = wishlist;
    }
    public LinkedHashMap<String, ArrayList<Book>> getReading() { return reading;}
    public void setReading(LinkedHashMap<String, ArrayList<Book>> reading) {
        this.reading = reading;
    }
    public User getUser() { return user; }
}
