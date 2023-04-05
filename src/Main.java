import model.*;
import service.BookService;
import service.Seed;
import service.Service;
import service.UserService;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Seed.getSeed();

        //Service.userManipulation();
        Service.shelfManipulation(UserService.getUsers().get(8));
        // afisare book frumos
        // verificare index corect
    }
}