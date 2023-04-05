import model.*;
import service.BookService;
import service.Seed;
import service.UserService;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Seed.getSeed();

        User user = UserService.registerUser();

        UserService.addCurrentlyReading(user);
        UserService.showCurrentlyReading(user);
    }
}