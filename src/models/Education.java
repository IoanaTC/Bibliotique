package models;

import models.enums.EducationDomain;

import java.util.HashSet;

public class Education {
    private EducationDomain domain;
    private HashSet<String> toolsincluded;

    public Education() {
        this.domain = null;
        this.toolsincluded = new HashSet<String>();
    }

    public Education(EducationDomain domain, HashSet<String> toolsincluded) {
        this.domain = domain;
        this.toolsincluded = toolsincluded;
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
