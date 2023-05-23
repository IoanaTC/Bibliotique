package model;

import model.enums.FictionDomain;

import java.util.Date;
import java.util.HashSet;

public class Fiction extends Book{
    private String domain;
    private String characters;
    private String allegory;

    public Fiction() {
        super();
        this.domain = null;
        this.allegory = "No allegory written for this book";
        this.characters = "None";
    }
    public Fiction(String title, String author, String description, Date published,
                   String domain, String characters, String allegory) {
        super(title, author, description, published);
        this.domain = domain;
        this.characters = characters;
        this.allegory = allegory;
    }
    public Fiction(String title, String author, String description, Date published,
                   String domain) {
        super(title, author, description, published);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getAllegory() {
        return allegory;
    }

    public void setAllegory(String allegory) {
        this.allegory = allegory;
    }
}
