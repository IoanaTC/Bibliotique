package model;

import model.enums.GraphicNovelDomain;

import java.util.Date;
import java.util.HashSet;

public class GraphicNovel extends Book{
    private String domain;
    // attribute that checks whether the comic book is
    // black and white or colored
    private String characters;
    private String style;

    public GraphicNovel() {
        this.domain = null;
        this.characters = "None found";
        this.style = "No style found for this graphic novel";
    }

    public GraphicNovel(String title, String author, String description, Date published,
                        String domain, String characters, String style) {
        super(title, author, description, published);
        this.domain = domain;
        this.characters = characters;
        this.style = style;
    }

    public GraphicNovel(String title, String author, Date published,
                        String domain, String style) {
        super(title, author, published);
        this.domain = domain;
        this.style = style;
        this.characters = "None found";
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
