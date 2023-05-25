package service;

import config.DatabaseConfiguration;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShelfService {
    public static void showCurrentlyReading(Shelf shelf){
        LinkedHashMap<String, ArrayList<Book>> currentlyReading = shelf.getReading();
        if(!currentlyReading.isEmpty()){
            int index = 1;

            System.out.println("These are you currently reading Fiction books:");
            ArrayList<Book> fiction = currentlyReading.get("fiction");
            if(!fiction.isEmpty()){
                for(Book book : fiction){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Fiction books on your currently reading shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are you currently reading Graphic Novel books:");
            ArrayList<Book> graphic = currentlyReading.get("graphic");
            if(!graphic.isEmpty()){
                for(Book book : graphic){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Graphic Novel books on your currently reading shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are you currently reading Education books:");
            ArrayList<Book> education = currentlyReading.get("education");
            if(!education.isEmpty()){
                for(Book book : education){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Education books on your currently reading shelf");
        }
        else System.out.println("Currently reading list: empty\n");
        System.out.println("-----------------------------------------------------------------------------------");
    }
    public static void addCurrentlyReading(Shelf shelf){
        BookService.showAllBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pick the numbers of the books you want to add " +
                "to your Currently Reading list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> currentlyReading = shelf.getReading();
        ArrayList<Book> fiction = currentlyReading.get("fiction");
        ArrayList<Book> graphic = currentlyReading.get("graphic");
        ArrayList<Book> education = currentlyReading.get("education");

        ArrayList<Book> books = BookService.getBooks();

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book_id < BookService.getFictionIndex()){
                    if(!fiction.contains(book)) {
                        fiction.add(book);
                        dbAdd(shelf.getUser(), BookService.getFictionId((Fiction) book), "reading", "fiction_id");
                    }

                } else if (book_id < BookService.getGraphicIndex()){
                    if(!graphic.contains(book)) {
                        graphic.add(book);
                        dbAdd(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "reading", "graphic_id");
                    }

                } else if (book_id < BookService.getEducationIndex()) {
                    if(!education.contains(book)) {
                        education.add(book);
                        dbAdd(shelf.getUser(), BookService.getEducationId((Education) book), "reading", "education_id");
                    }
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    public static void deleteCurrentlyReading(Shelf shelf){
        Scanner scanner = new Scanner(System.in);

        showCurrentlyReading(shelf);
        System.out.print("Pick the number of the book you want to delete " +
                "from your Currently Reading list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> currentlyReading = shelf.getReading();
        ArrayList<Book> books = new ArrayList<Book>(currentlyReading.get("fiction"));
        books.addAll(currentlyReading.get("graphic"));
        books.addAll(currentlyReading.get("education"));

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book instanceof Fiction){
                    dbDelete(shelf.getUser(), BookService.getFictionId((Fiction) book), "reading", "fiction_id");

                } else if (book instanceof GraphicNovel){
                    dbDelete(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "reading", "graphic_id");

                } else if (book instanceof Education) {
                    dbDelete(shelf.getUser(), BookService.getEducationId((Education) book), "reading", "education_id");
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    public static void showFavourites(Shelf shelf){
        LinkedHashMap<String, ArrayList<Book>> favourites = shelf.getFavourites();
        if(!favourites.isEmpty()){
            int index = 1;

            System.out.println("These are your favourite Fiction books:");
            ArrayList<Book> fiction = favourites.get("fiction");
            if(!fiction.isEmpty()){
                for(Book book : fiction){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Fiction books on your favourites shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are your favourite Graphic Novel books:");
            ArrayList<Book> graphic = favourites.get("graphic");
            if(!graphic.isEmpty()){
                for(Book book : graphic){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Graphic Novel books on your favourites shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are your favourite Education books:");
            ArrayList<Book> education = favourites.get("education");
            if(!education.isEmpty()){
                for(Book book : education){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Education books on your favourites shelf");
        }
        else System.out.println("Favourites list: empty\n");
        System.out.println("-----------------------------------------------------------------------------------");
    }
    public static void addFavourites(Shelf shelf){
        BookService.showAllBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pick the numbers of the books you want to add " +
                "to your Favourites list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> favourites = shelf.getFavourites();
        ArrayList<Book> fiction = favourites.get("fiction");
        ArrayList<Book> graphic = favourites.get("graphic");
        ArrayList<Book> education = favourites.get("education");

        ArrayList<Book> books = BookService.getBooks();

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book_id < BookService.getFictionIndex()){
                    if(!fiction.contains(book)) {
                        fiction.add(book);
                        dbAdd(shelf.getUser(), BookService.getFictionId((Fiction) book), "favourites", "fiction_id");
                    }

                } else if (book_id < BookService.getGraphicIndex()){
                    if(!graphic.contains(book)) {
                        graphic.add(book);
                        dbAdd(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "favourites", "graphic_id");
                    }

                } else if (book_id < BookService.getEducationIndex()) {
                    if(!education.contains(book)) {
                        education.add(book);
                        dbAdd(shelf.getUser(), BookService.getEducationId((Education) book), "favourites", "education_id");
                    }
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    public static void deleteFavourites(Shelf shelf){
        Scanner scanner = new Scanner(System.in);

        showFavourites(shelf);
        System.out.print("Pick the number of the book you want to delete " +
                "from your Favourites list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> favourites = shelf.getFavourites();
        ArrayList<Book> books = new ArrayList<Book>(favourites.get("fiction"));
        books.addAll(favourites.get("graphic"));
        books.addAll(favourites.get("education"));

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book instanceof Fiction){
                    dbDelete(shelf.getUser(), BookService.getFictionId((Fiction) book), "favourites", "fiction_id");

                } else if (book instanceof GraphicNovel){
                    dbDelete(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "favourites", "graphic_id");

                } else if (book instanceof Education) {
                    dbDelete(shelf.getUser(), BookService.getEducationId((Education) book), "favourites", "education_id");
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    public static void showWishlist(Shelf shelf){
        LinkedHashMap<String, ArrayList<Book>> wishlist = shelf.getWishlist();
        if(!wishlist.isEmpty()){
            int index = 1;

            System.out.println("These are your wishlist Fiction books:");
            ArrayList<Book> fiction = wishlist.get("fiction");
            if(!fiction.isEmpty()){
                for(Book book : fiction){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Fiction books on your wishlist shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are your wishlist Graphic Novel books:");
            ArrayList<Book> graphic = wishlist.get("graphic");
            if(!graphic.isEmpty()){
                for(Book book : graphic){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Graphic Novel books on your wishlist shelf");
            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println("These are your wishlist reading Education books:");
            ArrayList<Book> education = wishlist.get("education");
            if(!education.isEmpty()){
                for(Book book : education){
                    System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
                }
            }
            else System.out.println("There are no Education books on your wishlist shelf");
        }
        else System.out.println("Wishlist list: empty\n");
        System.out.println("-----------------------------------------------------------------------------------");
    }
    public static void addWishlist(Shelf shelf){
        BookService.showAllBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pick the numbers of the books you want to add " +
                "to your Wishlist: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> wishlist = shelf.getWishlist();
        ArrayList<Book> fiction = wishlist.get("fiction");
        ArrayList<Book> graphic = wishlist.get("graphic");
        ArrayList<Book> education = wishlist.get("education");

        ArrayList<Book> books = BookService.getBooks();

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book_id < BookService.getFictionIndex()){
                    if(!fiction.contains(book)) {
                        fiction.add(book);
                        dbAdd(shelf.getUser(), BookService.getFictionId((Fiction) book), "wishlist", "fiction_id");
                    }

                } else if (book_id < BookService.getGraphicIndex()){
                    if(!graphic.contains(book)) {
                        graphic.add(book);
                        dbAdd(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "wishlist", "graphic_id");
                    }

                } else if (book_id < BookService.getEducationIndex()) {
                    if(!education.contains(book)) {
                        education.add(book);
                        dbAdd(shelf.getUser(), BookService.getEducationId((Education) book), "wishlist", "education_id");
                    }
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    public static void deleteWishlist(Shelf shelf){
        Scanner scanner = new Scanner(System.in);

        showWishlist(shelf);
        System.out.print("Pick the number of the book you want to delete " +
                "from your Wishlist: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        LinkedHashMap<String, ArrayList<Book>> wishlist = shelf.getWishlist();
        ArrayList<Book> books = new ArrayList<Book>(wishlist.get("fiction"));
        books.addAll(wishlist.get("graphic"));
        books.addAll(wishlist.get("education"));

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            try {
                int book_id = index - 1;
                Book book = books.get(book_id);

                if(book instanceof Fiction){
                    dbDelete(shelf.getUser(), BookService.getFictionId((Fiction) book), "wishlist", "fiction_id");

                } else if (book instanceof GraphicNovel){
                    dbDelete(shelf.getUser(), BookService.getGraphicId((GraphicNovel) book), "wishlist", "graphic_id");

                } else if (book instanceof Education) {
                    dbDelete(shelf.getUser(), BookService.getEducationId((Education) book), "wishlist", "education_id");
                }
                System.out.println("Done");
            }
            catch(IndexOutOfBoundsException exception){
                System.out.printf("\nThis index, %d, is out of bounds\n", index);
            }
        }
    }
    private static void dbAdd(User user, int book_id, String tableName, String columnName){
        String insertSql = "INSERT INTO " + tableName + " (user_id, "  + columnName + ") VALUES(?, ?);";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, UserService.getUserId(user));
            preparedStatement.setInt(2, book_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void dbDelete(User user, int book_id, String tableName, String columnName){
        String insertSql = "DELETE FROM " + tableName + " WHERE " + columnName + " = ? and user_id = ?;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, book_id);
            preparedStatement.setInt(2, UserService.getUserId(user));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
