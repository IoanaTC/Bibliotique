package model;

import java.util.HashSet;

public class Club {
    private String name;
    private String motto;
    private String photo;

    public Club() {
        name = "No name";
        motto = "When life gives you lemons, make lemonade.";
        photo = null;
    }
    public Club(String name, String motto) {
        this.name = name;
        this.motto = motto;
        photo = null;
    }
    public Club(String name, String motto, String photo) {
        this.name = name;
        this.motto = motto;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}