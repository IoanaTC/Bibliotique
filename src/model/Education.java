package model;

import model.enums.EducationDomain;

import java.util.Date;
import java.util.HashSet;

public class Education extends Book{
    private String domain;
    private String tools;

    public Education() {
        super();
        this.domain = null;
        this.tools = "None";
    }

    public Education(String title, String author, String description, Date published,
                     String domain, String tools) {
        super(title, author, description, published);
        this.domain = domain;

        this.tools = tools;
    }

    public Education(String title, String author, Date published,
                     String domain, String tools) {
        super(title, author, published);
        this.domain = domain;
        this.tools = tools;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }
}
