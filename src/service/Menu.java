package service;

import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    // show all users, add new user, delete user, show user
    public static void userManipulation(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the User Management System!");
            showBook();
            System.out.println("Please select an option:");
            System.out.println("1. Show all users");
            System.out.println("2. Register a new user");
            System.out.println("3. Delete a user");
            System.out.println("4. Show a user's details");
            System.out.println("5. Update a chosen user's data");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Show all users
                    System.out.println("Showing all users...");
                    UserService.showAllUsers();
                }
                case 2 -> {
                    // Register a new user
                    System.out.println("Registering a new user...");
                    UserService.registerUser();
                }
                case 3 -> {
                    // Delete a user
                    System.out.println("Deleting a user...");
                    UserService.deleteUser();
                }
                case 4 -> {
                    // Show a user's details
                    System.out.println("Showing the chosen user's details...");
                    UserService.showAllUsers();
                    System.out.print("Pick the number of the user you want to find more about: ");
                    boolean answered = false;
                    while (!answered) {
                        int indexFound = scanner.nextInt() - 1;
                        try {
                            UserService.showUserDetailed(UserService.getUsers().get(indexFound));
                            answered = true;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index out of bounds, please insert a correct number : ");
                        }
                    }
                }
                case 5 -> {
                    // Update a user
                    System.out.println("Updating a user...");
                    UserService.updateUser();
                }
                case 0 -> {
                    // Exit the program
                    System.out.println("Bye");
                    showMagarus();
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
    public static void generalMenu() {
        Scanner scanner = new Scanner(System.in);

        User user = new User();
        ArrayList<User> users = UserService.getUsers();
        UserService.showAllUsers();

        System.out.print("\nPick the user you wish to log in as amongst the elements of this list, and insert its index : ");
        boolean indexFound = false;

        while(!indexFound){
            int user_id = scanner.nextInt() - 1;
            try{
                user = users.get(user_id);
                indexFound = true;
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Index out of bounds, please insert a correct number : ");
            }
        }
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the User's shelf Management System!");

            System.out.printf("Hello, %s %s\n", user.getFirstname(), user.getSurname());
            showBook();
            System.out.println("Select an option:");
            System.out.println("0. Enter User-Manipulation Menu\n");
            System.out.println("1. Add book to currently reading list");
            System.out.println("2. Delete book from currently reading list");
            System.out.println("3. Show currently reading list");
            System.out.println("4. Add book to favorites list");
            System.out.println("5. Delete book from favorites list");
            System.out.println("6. Show favorites list");
            System.out.println("7. Add book to wishlist");
            System.out.println("8. Delete book from wishlist");
            System.out.println("9. Show wishlist\n");
            System.out.println("10. Log-out and log back in as other user");
            System.out.println("-1. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> userManipulation();
                case 1 -> UserService.addCurrentlyReading(user);
                case 2 -> UserService.deleteCurrentlyReading(user);
                case 3 -> UserService.showCurrentlyReading(user);
                case 4 -> UserService.addFavourites(user);
                case 5 -> UserService.deleteFavourites(user);
                case 6 -> UserService.showFavourites(user);
                case 7 -> UserService.addWishlist(user);
                case 8 -> UserService.deleteWishlist(user);
                case 9 -> UserService.showWishlist(user);
                case -1 -> {
                    exit = true;
                    System.out.println("Bye");
                    showMagarus();
                }
                case 10 -> generalMenu();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void showBook(){
        System.out.println("     __...--~~~~~-._   _.-~~~~~--...__");
        System.out.println("    //               `V'             \\\\");
        System.out.println("   //                 |                \\\\");
        System.out.println("  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\");
        System.out.println(" //__.....----~~~~._\\\\|//_.~~~~----...__..\\\\");
        System.out.println("// =================\\\\|//==================\\\\");
        System.out.println("                    `---`                     ");
    }
    public static void showDog(){
        System.out.println(" ,-.___,-.");
        System.out.println("\\_/_ _\\_/");
        System.out.println("  )O_O(");
        System.out.println(" { (_) }");
        System.out.println("  `-^-'  ");
    }
    public static void showMagarus(){
        System.out.println(" __________");
        System.out.println("/ See ya! \\");
        System.out.println("\\__________/");
        System.out.println("        \\");
        System.out.println("         \\");
        System.out.println("    ^__^");
        System.out.println("    (oo)\\_______");
        System.out.println("    (__)\\       )\\/\\");
        System.out.println("        ||------w |");
        System.out.println("        ||       ||");

    }
}
