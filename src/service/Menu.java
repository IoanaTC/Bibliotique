package service;

import model.User;

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
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show all users
                    System.out.println("Showing all users...");
                    UserService.showAllUsers();
                    break;
                case 2:
                    // Register a new user
                    System.out.println("Registering a new user...");
                    UserService.registerUser();
                    break;
                case 3:
                    // Delete a user
                    System.out.println("Deleting a user...");
//                    UserService.deleteUser();
                    break;
                case 4:
                    // Show a user's details
                    System.out.println("Showing the chosen user's details...");

                    UserService.showAllUsers();
                    System.out.print("Pick the number of the user you want to find more about: ");
                    boolean answered = false;

                    while(!answered) {
                        int indexFound = scanner.nextInt() - 1;
                        try {
                            UserService.showUserDetailed(UserService.getUsers().get(indexFound));
                            answered = true;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index out of bounds, please insert a correct number : ");
                        }
                    }
                    break;
                case 0:
                    // Exit the program
                    System.out.println("Bye");
                    showMagarus();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
    public static void shelfManipulation(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the User's shelf Management System!");
            showBook();
            System.out.println("Select an option:");
            System.out.println("1. Add book to currently reading list");
            System.out.println("2. Delete book from currently reading list");
            System.out.println("3. Show currently reading list");
            System.out.println("4. Add book to favorites list");
            System.out.println("5. Delete book from favorites list");
            System.out.println("6. Show favorites list");
            System.out.println("7. Add book to wishlist");
            System.out.println("8. Delete book from wishlist");
            System.out.println("9. Show wishlist");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    UserService.addCurrentlyReading(user);
                    break;
                case 2:
                    UserService.deleteCurrentlyReading(user);
                    break;
                case 3:
                    UserService.showCurrentlyReading(user);
                    break;
                case 4:
                    UserService.addFavourites(user);
                    break;
                case 5:
                    UserService.deleteFavourites(user);
                    break;
                case 6:
                    UserService.showFavourites(user);
                    break;
                case 7:
                    UserService.addWishlist(user);
                    break;
                case 8:
                    UserService.deleteWishlist(user);
                    break;
                case 9:
                    UserService.showWishlist(user);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Bye");
                    showMagarus();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
