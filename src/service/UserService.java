package service;

import model.Book;
import model.BookClub;
import model.Review;
import model.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static ArrayList<User> users;
    public static ArrayList<User> getUsers(){
        if(users == null)
            users = new ArrayList<>();
        return users;
    }
    // register new user functionality
    public static User registerUser(){
        User newUser = new User();
        Scanner scanner = new Scanner(System.in);

        // reading the user's fullname and separating it into firstname and surname
        System.out.print("What is your name: ");
        String fullname = scanner.nextLine();
        Pattern pattern = Pattern.compile("(\\p{L}+) (\\p{L}+)");
        Matcher matcher = pattern.matcher(fullname);

        // the string matches -> name was succesfully set
        if(matcher.find()){
            newUser.setFirstname(matcher.group(1));
            newUser.setSurname(matcher.group(2));
        }
        else{
            // redirect user to re-enter its name
            System.out.println("Please insert your name in this format: Firstname Surname");
            newUser = registerUser();

            users.add(newUser);
            return newUser;
        }

        // setting users age
        System.out.print("How old are you: ");
        newUser.setAge(scanner.nextInt());

        // setting up the shelf
        newUser.getShelf();

        users.add(newUser);
        return newUser;
    }
    public static void deleteUser(){
        System.out.println("Deleting user...");
        Scanner scanner = new Scanner(System.in);

        showAllUsers();
        System.out.print("Pick the number of the user you want to delete: ");

        String collection = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(collection);

        while(matcher.find()){
            int index = Integer.parseInt(matcher.group());
            users.remove(index - 1);
        }
        System.out.println("Done");
    }
    public static void deleteUser(User user){
        System.out.println("Deleting user...");
        users.remove(user);
        System.out.println("Done");
    }
    public static void showAllUsers(){
        System.out.println("The currently registered users are:");
        int index = 1;
        for(User user : users){
            System.out.printf("%d. ", index++);
            showUser(user);
        }
    }
    public static void setCompany(User user){
        System.out.printf("Setting up the company details for you, %s\n", user.getFirstname());
        CompanyService.setCompany(user);
    }
    // show functionalities for current user
    public static void showUser(User user){
//        System.out.println("These are the current user's detalils:");
        System.out.printf("%s %s; %d ani\n",
                user.getFirstname(), user.getSurname(), user.getAge());

        if(user.getCompany() != null) {
            System.out.printf("Company name: %s - %s\n",
                    user.getCompany().getName(), user.getCompany().getDomain());
        }
    }
    public static void showCurrentlyReading(User user){
        System.out.printf("%s is currently reading:\n", user.getFirstname());
        ShelfService.showCurrentlyReading(user.getShelf());
    }
    public static void addCurrentlyReading(User user){
        System.out.printf("Adding a book to your Currently Reading shelf, %s...\n", user.getFirstname());
        ShelfService.addCurrentlyReading(user.getShelf());
    }
    public static void deleteCurrentlyReading(User user){
        System.out.printf("Deleting a book from your Currently Reading shelf, %s...\n", user.getFirstname());
        ShelfService.deleteCurentlyReading(user.getShelf());
    }
    public static void showFavourites(User user){
        System.out.printf("%s's favourites are:\n", user.getFirstname());
        ShelfService.showFavourites(user.getShelf());
    }
    public static void addFavourites(User user){
        System.out.printf("Adding a book to your Favourites shelf, %s...\n", user.getFirstname());
        ShelfService.addFavourites(user.getShelf());
    }
    public static void deleteFavourites(User user){
        System.out.printf("Deleting a book from your Favourites shelf, %s...\n", user.getFirstname());
        ShelfService.deleteFavourites(user.getShelf());
    }
    public static void showWishlist(User user){
        System.out.printf("%s wishes to read:\n", user.getFirstname());
        ShelfService.showWishlist(user.getShelf());
    }
    public static void addWishlist(User user){
        System.out.printf("Adding a book to your Wishlist shelf, %s...\n", user.getFirstname());
        ShelfService.addWishlist(user.getShelf());
    }
    public static void deleteWishlist(User user){
        System.out.printf("Deleting a book from your Wishlist shelf, %s...\n", user.getFirstname());
        ShelfService.deleteWishlist(user.getShelf());
    }
    public static void showBookClubs(User user){
        System.out.printf("%s is currently a member of:\n", user.getFirstname());
        ArrayList<BookClub> bookClubs = user.getBookClubs();
        if(!bookClubs.isEmpty()){
            int index = 1;
            for(BookClub club : bookClubs){
                System.out.printf("%d. %s\n", index++, club.getName());
            }
        }
        else System.out.println("No clubs found");
    }
    public static void leaveBookClub(User user){
        Scanner scanner = new Scanner(System.in);

        System.out.println("These are the clubs you've enroled in:");
        showBookClubs(user);

        System.out.print("Pick the number of the club you want to leave: ");
        int index = scanner.nextInt();
        user.getBookClubs().remove(index);
    }
    public static void joinBookClub(User user){
        Scanner scanner = new Scanner(System.in);

        BookClubService.showBookClubs();

        System.out.print("Pick the number of the club you want to join: ");
        int index = scanner.nextInt();

        user.getBookClubs().add(BookClubService.getBookClubs().get(index));
        BookClubService.addMember(index, user);
    }
    public static void createBookClub(User user){
        BookClubService.createBookClub(user);
    }
    public static void showReviews(User user){
        System.out.printf("%s has left these reviews:\n", user.getFirstname());
        Map<String, Review> Reviews = user.getReviews();
        if(!Reviews.isEmpty()){
            int index = 1;
            for(Map.Entry<String, Review> review : Reviews.entrySet()){
                System.out.printf("%d. Book: %s\n", index++, review.getKey());
                ReviewService.showReview(review.getValue());
            }
        }
        else System.out.println("No clubs found");
    }
    public static void addReview(User user){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating review...");

        System.out.println("Choose a book title: ");
        String title = scanner.nextLine();
        user.getReviews().put(title, ReviewService.addReview());
    }
    public static void deleteReview(User user){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Deleting review...");

        System.out.println("Choose a book title: ");
        String title = scanner.nextLine();

        user.getReviews().remove(title);
        System.out.println("Done");
    }
}
