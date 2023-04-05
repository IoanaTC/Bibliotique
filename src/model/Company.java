package model;

import java.util.HashSet;

public class Company {
    private String name;
    private String domain;

    public Company() {
        name = "Name unknown";
        domain = "Domain unknown";
    }

    public Company(String name, String domain) {
        this.name = name;
        this.domain = domain;
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
    }
