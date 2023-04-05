package models;

import java.util.HashSet;

public class BookClub {
    private String name;
    private String motto;
    private String coverphoto;

    // a set of users that are members of this book club
    private HashSet<User> users;

    public BookClub() {
        name = "No name";
        motto = "When life gives you lemons, make lemonade.";
        coverphoto = null;
    }
    public BookClub(String name, String motto) {
        this.name = name;
        this.motto = motto;
        coverphoto = null;
    }
    public BookClub(String name, String motto, String coverphoto) {
        this.name = name;
        this.motto = motto;
        this.coverphoto = coverphoto;
    }
    {
        users = new HashSet<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }
}
