package service;

import model.Book;
import model.Shelf;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShelfService {
    public static void showCurrentlyReading(Shelf shelf){
        ArrayList<Book> currentlyReading = shelf.getReading();
        if(!currentlyReading.isEmpty()){
            int index = 1;
            for(Book book : currentlyReading){
                System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
            }
        }
        else System.out.println("Currently reading list: empty\n");
    }
    public static void addCurrentlyReading(Shelf shelf){
        BookService.showBooks();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pick the numbers of the books you want to add " +
                "to your Currently Reading list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        ArrayList<Book> currentlyReading = shelf.getReading();
        ArrayList<Book> books = BookService.getBooks();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index > books.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            if(!currentlyReading.contains(books.get(index - 1)))
                currentlyReading.add(books.get(index - 1));
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

        ArrayList<Book> currentlyReading = shelf.getReading();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index >= currentlyReading.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            currentlyReading.remove(index - 1);
        }
    }
    public static void showFavourites(Shelf shelf){
        ArrayList<Book> Favourites = shelf.getFavourites();
        if(!Favourites.isEmpty()){
            int index = 1;
            for(Book book : Favourites){
                System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
            }
        }
        else System.out.println("Favourites list: empty");
    }
    public static void addFavourites(Shelf shelf){
        Scanner scanner = new Scanner(System.in);

        BookService.showBooks();
        System.out.print("Pick the number of the book you want to add " +
                "to your Favourites list: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        ArrayList<Book> Favourites = shelf.getFavourites();
        ArrayList<Book> books = BookService.getBooks();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index > books.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            if(!Favourites.contains(books.get(index - 1)))
                Favourites.add(books.get(index - 1));
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

        ArrayList<Book> Favourites = shelf.getFavourites();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index >= Favourites.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            Favourites.remove(index - 1);
        }
    }
    public static void showWishlist(Shelf shelf){
        ArrayList<Book> Wishlist = shelf.getWishlist();
        if(!Wishlist.isEmpty()){
            int index = 1;
            for(Book book : Wishlist){
                System.out.printf("%d. %s, %s\n", index++, book.getTitle(), book.getAuthor());
            }
        }
        else System.out.println("Wishlist: empty");
    }
    public static void addWishlist(Shelf shelf){
        Scanner scanner = new Scanner(System.in);

        BookService.showBooks();
        System.out.print("Pick the number of the book you want to add " +
                "to your Wishlist: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        ArrayList<Book> Wishlist = shelf.getWishlist();
        ArrayList<Book> books = BookService.getBooks();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index > books.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            if(!Wishlist.contains(books.get(index - 1)))
                Wishlist.add(books.get(index - 1));
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

        ArrayList<Book> Wishlist = shelf.getWishlist();
        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());

            if(index >= Wishlist.size()){
                System.out.println("Index out of bounds");
                continue;
            }
            Wishlist.remove(index - 1);
        }
    }
}
