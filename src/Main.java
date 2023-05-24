import service.Seed;
import service.Menu;
import service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        Seed.getSeed();

        Menu.userManipulation();
//        Menu.shelfManipulation(UserService.getUsers().get(8));

//        User user = new User();
//        System.out.println(user.getShelf());
//        System.out.println(user.getShelf());

    }
}