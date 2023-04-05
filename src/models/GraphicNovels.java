package models;

import models.enums.GraphicNovelsDomain;

import java.util.HashSet;

public class GraphicNovels extends Book{
    private GraphicNovelsDomain domain;
    // attribute that checks whether the comic book is
    // black and white or colored
    private boolean colorscheme;
    private HashSet<String> characters;
    private String style;
}
