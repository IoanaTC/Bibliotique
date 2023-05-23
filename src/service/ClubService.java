package service;

import model.Club;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ClubService {
    private static ArrayList<Club> bookClubs;
    public static ArrayList<Club> getBookClubs(){
        if(bookClubs == null)
            bookClubs = new ArrayList<>();
        return bookClubs;
    }
    public static void showBookClub(Club bookClub){
        System.out.println(bookClub.getName());
        System.out.println("->"+bookClub.getMotto()+"<-");

        System.out.println("Members:");
        int index = 1;

    }
    public static void addMember(int index, User user){
        Club bookClub = bookClubs.get(index);
        System.out.println("Member added");
    }
    public static void createBookClub(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a new Book Club right away:");

        Club bookClub = new Club();
        System.out.print("Name: "); bookClub.setName(scanner.nextLine());
        System.out.print("Motto: "); bookClub.setMotto(scanner.nextLine());

        bookClubs.add(bookClub);
        System.out.println("Done");
    }
    public static void showBookClubs(){
        System.out.println("These are the currently created clubs:");
        for(Club bookclub : bookClubs){
            showBookClub(bookclub);
        }
    }
}