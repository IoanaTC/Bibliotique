package service;

import config.DatabaseConfiguration;
import model.Club;
import model.Company;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ClubService {
//    public static void showBookClub(Club bookClub){
//        System.out.println(bookClub.getName());
//        System.out.println("->"+bookClub.getMotto()+"<-");
//
//        System.out.println("Members:");
//        int index = 1;
//
//    }
//    public static void addMember(int index, User user){
//        Club bookClub = bookClubs.get(index);
//        System.out.println("Member added");
//    }
//    public static void createBookClub(User user){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Creating a new Book Club right away:");
//
//        Club bookClub = new Club();
//        System.out.print("Name: "); bookClub.setName(scanner.nextLine());
//        System.out.print("Motto: "); bookClub.setMotto(scanner.nextLine());
//
//        bookClubs.add(bookClub);
//        System.out.println("Done");
//    }
    public static ArrayList<Club> getBookClubs() {
        ArrayList<Club> clubs = new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT * FROM clubs";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String motto = resultSet.getString("motto");
                String photo = resultSet.getString("photo");

                Club newCLub = new Club(name, motto, photo);
                clubs.add(newCLub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }
    public static void showBookClubs(ArrayList<Club> clubs) {
        int index = 1;
        for(Club club : clubs) {
            System.out.printf("%d. ", index++);
            System.out.println("Name: " + club.getName() + "; Motto: " + club.getMotto() + "; Photo: "
                    + club.getPhoto());
        }
    }
    public static int getClubId(Club club){
        int index = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT id FROM clubs WHERE name = ? and motto = ? and photo = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, club.getName());
            preparedStatement.setString(2, club.getMotto());
            preparedStatement.setString(3, club.getPhoto());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            index = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
    public static Club getClubById(int club_id){
        Club club = new Club();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT name,motto,photo FROM clubs WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, club_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            club.setName(resultSet.getString(1));
            club.setMotto(resultSet.getString(2));
            club.setPhoto(resultSet.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return club;
    }
}