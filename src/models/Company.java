package models;

import java.util.HashSet;

public class Company {
    private String name;
    private String domain;
    private HashSet<User> employees;

    public Company() {
        name = "Name unknown";
        domain = "Domain unknown";
    }

    public Company(String name, String domain, HashSet<User> employees) {
        this.name = name;
        this.domain = domain;
    }
    {
        employees = new HashSet<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public HashSet<User> getEmployees() {
        return employees;
    }

    public void setEmployees(HashSet<User> employees) {
        this.employees = employees;
    }
}
