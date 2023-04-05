package model;

import model.enums.EducationDomain;

import java.util.Date;
import java.util.HashSet;

public class Education extends Book{
    private EducationDomain domain;
    private HashSet<String> toolsincluded;

    public Education() {
        this.domain = null;
        this.toolsincluded = new HashSet<String>();
    }

    public Education(String title, Author author, String description, Date publishedon,
                     EducationDomain domain, HashSet<String> toolsincluded) {
        super(title, author, description, publishedon);
        this.domain = domain;
        this.toolsincluded = toolsincluded;
    }

    public Education(String title, Author author, Date publishedon,
                     EducationDomain domain) {
        super(title, author, publishedon);
        this.domain = domain;
        this.toolsincluded = new HashSet<String>();
    }

    public EducationDomain getDomain() {
        return domain;
    }

    public void setDomain(EducationDomain domain) {
        this.domain = domain;
    }

    public HashSet<String> getToolsincluded() {
        return toolsincluded;
    }

    public void setToolsincluded(HashSet<String> toolsincluded) {
        this.toolsincluded = toolsincluded;
    }
}
