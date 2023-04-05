package service;

import model.Book;

import java.util.ArrayList;

public class BookService {
    private static ArrayList<Book> books;
    public static ArrayList<Book> getBooks(){
        if(books == null)
            books = new ArrayList<>();
        return books;
    }
    public static void showBooks(){
        System.out.println("These are the available books for choosing:");
        int index = 1;
        for(Book book : books){
            System.out.printf("%d. %s - %s\n", index++, book.getTitle(), book.getAuthor().getName());
        }
    }
}
