package service;

import model.User;

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
}
