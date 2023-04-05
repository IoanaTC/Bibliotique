package service;

import model.*;
import model.enums.EducationDomain;
import model.enums.FictionDomain;
import model.enums.GraphicNovelDomain;

import java.util.*;

public class Seed {
    private static Seed seed;
    private Seed(){
        UserService.getUsers();
        BookService.getBooks();
        BookClubService.getBookClubs();

        // add entry books
        ArrayList<Book> books = BookService.getBooks();
        books.add(new Fiction("Harry Potter and the Philosopher's Stone", new Author("J.K. Rowling"), "A boy discovers he's a wizard and attends a school of magic.", new Date(1997, Calendar.JUNE, 26), FictionDomain.ADVENTURE));
        books.add(new Fiction("The Da Vinci Code", new Author("Dan Brown"), "A symbologist tries to uncover a conspiracy involving the Holy Grail.", new Date(2003, Calendar.MARCH, 18), FictionDomain.THRILLER));
        books.add(new Fiction("Pride and Prejudice", new Author("Jane Austen"), "A love story set in Georgian England.", new Date(1813, Calendar.JANUARY, 28), FictionDomain.ROMANCE));
        books.add(new Fiction("The Shining", new Author("Stephen King"), "A family becomes trapped in a haunted hotel during the winter.", new Date(1977, Calendar.JANUARY, 28), FictionDomain.HORROR));
        books.add(new Fiction("The Hitchhiker's Guide to the Galaxy", new Author("Douglas Adams"), "The misadventures of an unwitting human and his alien friend.", new Date(1979, Calendar.OCTOBER, 12), FictionDomain.COMEDY));
        books.add(new Fiction("Omul de la capătul firului", new Author("Rodica Ojog-Brasoveanu"),"Un plan cu adevarat diabolic in avion", new Date(1973), FictionDomain.THRILLER));
        books.add(new Education("Steve Jobs", new Author("Walter Isaacson"), new Date(2011, Calendar.OCTOBER, 24), EducationDomain.BIOGRAPHY, new HashSet<>(Arrays.asList("Apple II", "Macintosh", "iPod", "iPhone", "iPad"))));
        books.add(new Education("The Lean Startup", new Author("Eric Ries"), new Date(2011, Calendar.SEPTEMBER, 13), EducationDomain.BUSINESS, new HashSet<>()));
        books.add(new Education("Clean Code: A Handbook of Agile Software Craftsmanship", new Author("Robert C. Martin"), new Date(2008, Calendar.AUGUST, 1), EducationDomain.IT, new HashSet<>(Arrays.asList("JUnit", "Mockito", "Jenkins", "SonarQube"))));
        books.add(new Education("Mastering the Art of French Cooking", new Author("Julia Child, Louisette Bertholle, and Simone Beck"), new Date(1961, Calendar.OCTOBER, 16), EducationDomain.COOKING, new HashSet<>()));
        books.add(new Education("The Vegetable Gardener's Bible", new Author("Edward C. Smith"), new Date(2000, Calendar.JANUARY, 1), EducationDomain.GARDENING, new HashSet<>(Arrays.asList("Hand pruners", "Garden fork", "Hose", "Trowel", "Garden gloves"))));
        books.add(new Education("Born to Run: A Hidden Tribe, Superathletes, and the Greatest Race the World Has Never Seen", new Author("Christopher McDougall"), new Date(2009, Calendar.MAY, 5), EducationDomain.SPORT, new HashSet<>()));
        books.add(new Education("Hands-On Machine Learning with Scikit-Learn", new Author("Geron Aurelien"), "Carte despre ML", new Date(2017, Calendar.MARCH, 2017), EducationDomain.IT, new HashSet<>()));
        books.add(new GraphicNovel("Naruto", new Author("Masashi Kishimoto"), new Date(2007), GraphicNovelDomain.MANGA,"B&W"));
        books.add(new GraphicNovel("Watchmen", new Author("Alan Moore"), "Set in an alternate history where superheroes are treated as outlaws.", new Date(1986, Calendar.SEPTEMBER, 1), GraphicNovelDomain.COMICS, new HashSet<>(Arrays.asList("Rorschach", "Dr. Manhattan", "The Comedian", "Silk Spectre")), "Color"));
        books.add(new GraphicNovel("One Piece", new Author("Eiichiro Oda"), "The story follows a group of pirates on their journey to find the One Piece.", new Date(1997, Calendar.JULY, 19), GraphicNovelDomain.MANGA, new HashSet<>(Arrays.asList("Monkey D. Luffy", "Roronoa Zoro", "Nami", "Sanji", "Usopp", "Tony Tony Chopper", "Nico Robin", "Franky", "Brook")), "Color"));
        books.add(new GraphicNovel("Death Note", new Author("Tsugumi Ohba"), "Detective thriller with supernatural elements", new Date(2003, Calendar.DECEMBER, 1), GraphicNovelDomain.MANGA, new HashSet<>(Arrays.asList("Light Yagami", "L", "Misa Amane", "Ryuk")), "Alb-negru"));
        books.add(new GraphicNovel("Sandman", new Author("Neil Gaiman"), "Dark fantasy and mythology", new Date(1989, Calendar.JANUARY, 1), GraphicNovelDomain.COMICS, new HashSet<>(Arrays.asList("Dream", "Death", "Desire", "Delirium")), "Color"));
        books.add(new GraphicNovel("V for Vendetta", new Author("Alan Moore"), "Set in a dystopian future United Kingdom, where a fascist regime has subjugated the country.", new Date(1988, Calendar.MARCH, 30), GraphicNovelDomain.COMICS, new HashSet<>(Arrays.asList("V", "Evey Hammond", "Inspector Finch")), "Color"));

        // add users
        ArrayList<User> users = UserService.getUsers();
        users.add(new User("Iorgu", "de la Sadagura", 55));
        users.add(new User("Costache", "Contra", 45));
        users.add(new User("Zaharia", "Trahanache", 60));
        users.add(new User("Agamemnon", "Dandanache", 50));
        users.add(new User("Leonida", "Pascalopol", 55));
        users.add(new User("Pristanda", "", 35));
        users.add(new User("Ghiță", "Pristanda", 25));
        users.add(new User("Tipătescu", "", 45));
        users.add(new User("Chiriac", "", 30));
        users.add(new User("Veta", "", 20));

        ArrayList<BookClub> bookClubs = BookClubService.getBookClubs();
        bookClubs.add(new BookClub("Clubul de carte Bibliofili", "Cărțile sunt universul nostru!"));
        bookClubs.add(new BookClub("Clubul de carte Amatorii Literaturii", "O carte pe zi ține doctorul departe!"));
        bookClubs.add(new BookClub("Clubul de carte Pasionații Poeziei", "În poezie găsim răspunsuri la întrebări inexprimabile!"));
        bookClubs.add(new BookClub("The Literary Society", "Bringing people together through the love of books"));
        bookClubs.add(new BookClub("The Bookworms", "Where reading is a way of life"));
        bookClubs.add(new BookClub("The Page Turners", "Discover new worlds through the magic of literature"));
        bookClubs.add(new BookClub("The Reading Circle", "A community of book enthusiasts"));
        bookClubs.add(new BookClub("The Bookish Bunch", "Sharing our passion for reading with the world"));

    }
    public static Seed getSeed(){
        if(seed == null)
            seed = new Seed();
        return seed;
    }
}
