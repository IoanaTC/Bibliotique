package service;

import config.DatabaseConfiguration;
import model.Club;
import model.Company;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService{
    public static ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT * FROM users";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                int company_id = resultSet.getInt("company_id");
                int club_id = resultSet.getInt("club_id");

                Company company = CompanyService.getCompanyById(company_id);
                Club club = ClubService.getClubById(club_id);

                User newUser = new User(firstname, surname, age, company, club);
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    // register new user functionality
    public static User registerUser(){
        User newUser = new User();
        Scanner scanner = new Scanner(System.in);

        // reading the user's full-name and separating it into firstname and surname
        System.out.print("What is your name: ");

        String full_name = scanner.nextLine();
        Pattern pattern = Pattern.compile("(\\p{L}+) (\\p{L}+)");
        Matcher matcher = pattern.matcher(full_name);

        // the string matches -> name was successfully set
        if(matcher.find()){
            newUser.setFirstname(matcher.group(1));
            newUser.setSurname(matcher.group(2));
        }
        else{
            // redirect user to re-enter its name
            System.out.println("Please insert your name in this format: Firstname Surname");
            newUser = registerUser();
            return newUser;
        }
        // setting users age
        System.out.print("How old are you: ");
        newUser.setAge(scanner.nextInt());

        System.out.print("Are you currently employed: 1 (true) or 0 (false) : ");
        boolean answered = false;
        while(!answered){
            int answer = scanner.nextInt();
            if (answer == 1) {
                ArrayList<Company> companies = CompanyService.getCompanies();
                CompanyService.showCompanies(companies);

                System.out.print("Find the company amongst the elements of this list, and insert its index : ");
                boolean indexFound = false;

                while(!indexFound){
                    int company_id = scanner.nextInt() - 1;
                    try{
                        newUser.setCompany(companies.get(company_id));
                        indexFound = true;
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Index out of bounds, please insert a correct number:");
                    }
                }
                answered = true;
            }
            else if(answer == 0){
                newUser.setCompany(null);
                answered = true;
            }
            else System.out.println("Invalid answer, please insert 0 (false) or 1 (true) : ");
        }

        System.out.print("Are you a member of any club: 1 (true) or 0 (false) : ");
        answered = false;
        while(!answered){
            int answer = scanner.nextInt();
            if (answer == 1) {
                ArrayList<Club> clubs = ClubService.getBookClubs();
                ClubService.showBookClubs(clubs);

                System.out.print("Find the club amongst the elements of this list, and insert its index : ");
                boolean indexFound = false;

                while(!indexFound){
                    int club_id = scanner.nextInt() - 1;
                    try{
                        newUser.setClub(clubs.get(club_id));
                        indexFound = true;
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Index out of bounds, please insert a correct number");
                    }
                }
                answered = true;
            }
            else if(answer == 0){
                newUser.setClub(null);
                answered = true;
            }
            else System.out.println("Invalid answer, please insert 0 (false) or 1 (true)");
        }
        dbCreate(newUser);
        System.out.printf("The user: %s %s has been created successfully", newUser.getFirstname(), newUser.getFirstname());
        return newUser;
    }
//    public static void deleteUser(){
//        System.out.println("Deleting user...");
//        Scanner scanner = new Scanner(System.in);
//
//        showAllUsers();
//        System.out.print("Pick the number of the user you want to delete: ");
//
//        String collection = scanner.nextLine();
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(collection);
//
//        while(matcher.find()){
//            int index = Integer.parseInt(matcher.group());
//
//            try {
//                users.remove(index - 1);
//            }
//            catch(ArrayIndexOutOfBoundsException exception){
//                System.out.println("Index out of bounds");
//            }
//        }
//        System.out.println("Done");
//    }
//
    public static void showAllUsers(){
        ArrayList<User> users = getUsers();

        System.out.println("The currently registered users are:");
        int index = 1;

        for (User user : users){
            System.out.printf("%d. ", index ++);
            showUser(user);
        }
    }

    // show functionalities for current user
    public static void showUser(User user) {
        System.out.printf("Nume: %s %s; Varsta: %d ani\n",
                user.getFirstname(), user.getSurname(), user.getAge());
    }
    public static void showUserDetailed(User user) {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Nume: %s %s; Varsta: %d ani\n\n",
                user.getFirstname(), user.getSurname(), user.getAge());

        if(!Objects.equals(user.getCompany().getName(), "None")) {
            System.out.printf("Company name: %s - %s\n",
                    user.getCompany().getName(), user.getCompany().getDomain());
        }
        else {
            System.out.println("This user is unemployed");
        }
        if(!Objects.equals(user.getClub().getName(), "None")) {
            System.out.printf("Club name: %s - %s\n",
                    user.getClub().getName(), user.getClub().getMotto());
        }
        else {
            System.out.println("This user has not enrolled in any club");
        }
        System.out.println("-----------------------------------------------------------------------------------");
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
        ShelfService.deleteCurrentlyReading(user.getShelf());
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

    private static void dbCreate(User user){
        String insertPersonSql = "INSERT INTO users(firstname, surname, age, company_id, club_id) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());

            int company_id = 1, club_id = 1;
            if(user.getCompany() != null){
                company_id = CompanyService.getCompanyId(user.getCompany());
            }
            if(user.getClub() != null){
                club_id = ClubService.getClubId(user.getClub());
            }
            preparedStatement.setInt(4, company_id);
            preparedStatement.setInt(5, club_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void dbUpdate(User user){

    }
    private static void dbDelete(User user){

    }
}
