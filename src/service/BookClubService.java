package service;

import model.BookClub;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BookClubService {
    private static ArrayList<BookClub> bookClubs;
    public static ArrayList<BookClub> getBookClubs(){
        if(bookClubs == null)
            bookClubs = new ArrayList<>();
        return bookClubs;
    }
    public static void showBookClub(BookClub bookClub){
        System.out.println(bookClub.getName());
        System.out.println("->"+bookClub.getMotto()+"<-");

        System.out.println("Members:");
        HashSet<User> members = bookClub.getUsers();
        int index = 1;
        for(User member : members){
            System.out.printf("%d. %s %s\n", index++, member.getFirstname(), member.getSurname());
        }
    }
    public static void addMember(int index, User user){
        BookClub bookClub = bookClubs.get(index);
        bookClub.getUsers().add(user);
        System.out.println("Member added");
    }
    public static void createBookClub(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a new Book Club right away:");

        BookClub bookClub = new BookClub();
        System.out.print("Name: "); bookClub.setName(scanner.nextLine());
        System.out.print("Motto: "); bookClub.setMotto(scanner.nextLine());

        bookClub.getUsers().add(user);
        bookClubs.add(bookClub);
        System.out.println("Done");
    }
    public static void showBookClubs(){
        System.out.println("These are the currently created clubs:");
        for(BookClub bookclub : bookClubs){
            showBookClub(bookclub);
        }
    }
}
