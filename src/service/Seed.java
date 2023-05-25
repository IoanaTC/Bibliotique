package service;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import config.DatabaseSeed;
import config.DatabaseConfiguration;

public final class Seed {
    private static Seed seed;
    private Seed() throws SQLException {
        createDatabase();
    }
    private void createDatabase() throws SQLException {
        DatabaseSeed database = DatabaseSeed.getDatabaseSchema();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        LinkedHashMap<String, String> structure = database.getStructure();
        for (Map.Entry<String, String> entry : structure.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if(tableDoesntExist(connection, key)){
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(value);
                    System.out.printf("Table %s created successfully%n", key);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.printf("Table %s already exists%n", key);
            }
        }
        DatabaseSeed.seedCompanies();
        DatabaseSeed.seedClubs();
        DatabaseSeed.seedFiction();
        DatabaseSeed.seedEducation();
        DatabaseSeed.seedGraphicNovel();
        DatabaseSeed.seedUsers();
    }

    private static boolean tableDoesntExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        return !metaData.getTables(null, null, tableName, null).next();
    }
    public static Seed getSeed() throws SQLException {
        if(seed == null)
            seed = new Seed();
        return seed;
    }
}
