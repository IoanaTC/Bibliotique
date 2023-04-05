package model;

import model.enums.GraphicNovelDomain;

import java.util.Date;
import java.util.HashSet;

public class GraphicNovel extends Book{
    private GraphicNovelDomain domain;
    // attribute that checks whether the comic book is
    // black and white or colored
    private boolean colorscheme;
    private HashSet<String> characters;
    private String style;

    public GraphicNovel() {
        this.domain = null;
        this.colorscheme = false;
        this.characters = new HashSet<String>();
        this.style = "No style found for this graphic novel";
    }

    public GraphicNovel(String title, Author author, String description, Date publishedon,
                        GraphicNovelDomain domain, boolean colorscheme, HashSet<String> characters, String style) {
        super(title, author, description, publishedon);
        this.domain = domain;
        this.colorscheme = colorscheme;
        this.characters = characters;
        this.style = style;
    }

    public GraphicNovel(String title, Author author, Date publishedon,
                        GraphicNovelDomain domain, boolean colorscheme, String style) {
        super(title, author, publishedon);
        this.domain = domain;
        this.colorscheme = colorscheme;
        this.style = style;
        this.characters = new HashSet<String>();
    }

    public GraphicNovelDomain getDomain() {
        return domain;
    }

    public void setDomain(GraphicNovelDomain domain) {
        this.domain = domain;
    }

    public boolean isColorscheme() {
        return colorscheme;
    }

    public void setColorscheme(boolean colorscheme) {
        this.colorscheme = colorscheme;
    }

    public HashSet<String> getCharacters() {
        return characters;
    }

    public void setCharacters(HashSet<String> characters) {
        this.characters = characters;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
