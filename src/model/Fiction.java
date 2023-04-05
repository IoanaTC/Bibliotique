package model;

import model.enums.FictionDomain;

import java.util.Date;
import java.util.HashSet;

public class Fiction extends Book{
    private FictionDomain domain;
    private HashSet<String> characters;
    private String allegory;

    public Fiction() {
        this.domain = null;
        this.allegory = "No allegory written for this book";
        this.characters = new HashSet<String>();
    }
    public Fiction(String title, Author author, String description, Date publishedon,
                   FictionDomain domain, HashSet<String> characters, String allegory) {
        super(title, author, description, publishedon);
        this.domain = domain;
        this.characters = characters;
        this.allegory = allegory;
    }
    public Fiction(String title, Author author, String description, Date publishedon,
                   FictionDomain domain) {
        super(title, author, description, publishedon);
        this.domain = domain;
        this.allegory = allegory;
        this.characters = new HashSet<String>();
    }

    public FictionDomain getDomain() {
        return domain;
    }

    public void setDomain(FictionDomain domain) {
        this.domain = domain;
    }

    public HashSet<String> getCharacters() {
        return characters;
    }

    public void setCharacters(HashSet<String> characters) {
        this.characters = characters;
    }

    public String getAllegory() {
        return allegory;
    }

    public void setAllegory(String allegory) {
        this.allegory = allegory;
    }
}
