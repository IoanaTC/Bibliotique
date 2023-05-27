import config.DatabaseConfiguration;
import config.DatabaseSeed;
import service.Seed;
import service.Menu;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Hello world!");
        Seed.getSeed();

        Menu.generalMenu();
        DatabaseSeed.getFile().close();
        DatabaseConfiguration.closeDatabaseConnection();
    }
}