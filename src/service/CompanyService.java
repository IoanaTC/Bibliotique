package service;

import config.DatabaseConfiguration;
import model.Company;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyService {
    public static void setCompany(User user){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Company name: ");
        user.getCompany().setName(scanner.nextLine());

        System.out.print("Domain: ");
        user.getCompany().setDomain(scanner.nextLine());
        scanner.close();
    }
    public static ArrayList<Company> getCompanies() {
        ArrayList<Company> companies = new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT * FROM companies";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String domain = resultSet.getString("domain");

                Company newCompany = new Company(name, domain);
                companies.add(newCompany);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
    public static void showCompanies(ArrayList<Company> companies) {
        int index = 1;
        for(Company company : companies) {
            System.out.printf("%d. ", index++);
            System.out.println("Name: " + company.getName() + "; Domain: " + company.getDomain());
        }
    }
    public static int getCompanyId(Company company){
        int index = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT id FROM companies WHERE name = '" + company.getName() +
                    "' and domain = '" + company.getDomain() + "';";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            resultSet.next();
            index = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
    public static Company getCompanyById(int company_id){
        Company company = new Company();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT name, domain FROM companies WHERE" + " id = " + company_id + ";";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            resultSet.next();
            company.setName(resultSet.getString(1));
            company.setDomain(resultSet.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }
}
