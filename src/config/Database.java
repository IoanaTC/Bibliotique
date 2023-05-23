package config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// building the structure of the database, it cannot be modified later
public class Database {
    // making this class a singleton, as we only need one database for this project
    private static Database INSTANCE;
    // the database structure is represented by a dictionary where the keys are table names
    // and the values are the query string used to create it
    private final LinkedHashMap<String, String> structure;

    public static Database getDatabaseSchema() {
        if(INSTANCE == null)
            INSTANCE = new Database();
        return INSTANCE;
    }
    public LinkedHashMap<String, String> getStructure(){
        return structure;
    }

    // creating the structure
    private Database(){
        structure = new LinkedHashMap<>();

        // the currently accepted books
        structure.put("fiction", getFictionTable());
        structure.put("education", getEducationTable());
        structure.put("graphic_novel", getGraphicNovelTable());

        // user related objects
        structure.put("companies", getCompanyTable());
        structure.put("clubs", getClubTable());

        structure.put("users", getUserTable());
        // associative tables
        structure.put("favourites", getFavouritesTable());
        structure.put("wishlist", getWishlistTable());
        structure.put("reading", getReadingTable());
    }
    private String getFictionTable() {
        return "CREATE TABLE fiction ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description TEXT,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "characters TEXT,"
                + "allegory TEXT"
                + ")";
    }
    private String getEducationTable() {
        return "CREATE TABLE education ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "tools VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getGraphicNovelTable() {
        return "CREATE TABLE graphic_novel ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "characters VARCHAR(255) NOT NULL,"
                + "style VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getCompanyTable() {
        return "CREATE TABLE companies ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "domain VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getClubTable() {
        return "CREATE TABLE clubs ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "motto VARCHAR(255) NOT NULL,"
                + "photo VARCHAR(255)"
                + ")";
    }
    private String getFavouritesTable(){
        return "CREATE TABLE favourites ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getWishlistTable(){
        return "CREATE TABLE wishlist ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getReadingTable(){
        return "CREATE TABLE reading ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getUserTable() {
        return "CREATE TABLE users ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "company_id INT,"
                + "club_id INT,"
                + "FOREIGN KEY (company_id) REFERENCES companies(id),"
                + "FOREIGN KEY (club_id) REFERENCES clubs(id)"
                + ")";
    }
}
