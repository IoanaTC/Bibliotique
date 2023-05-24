package config;

import model.enums.EducationDomain;
import model.enums.FictionDomain;
import model.enums.GraphicNovelDomain;
import service.ClubService;
import service.CompanyService;

import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

// building the structure of the database, it cannot be modified later
public class DatabaseSeed {
    // making this class a singleton, as we only need one database for this project
    private static DatabaseSeed INSTANCE;
    // the database structure is represented by a dictionary where the keys are table names
    // and the values are the query string used to create it
    private final LinkedHashMap<String, String> structure;

    public static DatabaseSeed getDatabaseSchema() {
        if(INSTANCE == null)
            INSTANCE = new DatabaseSeed();
        return INSTANCE;
    }
    // using a linked hash map in order to have a dictionary that has all its elements in the order of addition
    public LinkedHashMap<String, String> getStructure(){
        return structure;
    }

    // creating the structure
    private DatabaseSeed(){
        structure = new LinkedHashMap<>();

        // the currently accepted types of books
        structure.put("fiction", getFictionTable());
        structure.put("education", getEducationTable());
        structure.put("graphic_novel", getGraphicNovelTable());

        // user related objects
        structure.put("companies", getCompanyTable());
        structure.put("clubs", getClubTable());

        structure.put("users", getUserTable());
        // associative tables: links between books and users
        structure.put("favourites", getFavouritesTable());
        structure.put("wishlist", getWishlistTable());
        structure.put("reading", getReadingTable());
    }
    private String getFictionTable() {
        return "CREATE TABLE fiction ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "available BOOLEAN NOT NULL,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "characters TEXT,"
                + "allegory TEXT"
                + ")";
    }
    private String getEducationTable() {
        return "CREATE TABLE education ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "available BOOLEAN NOT NULL,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "tools VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getGraphicNovelTable() {
        return "CREATE TABLE graphic_novel ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "title VARCHAR(255) NOT NULL,"
                + "author VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "available BOOLEAN NOT NULL,"
                + "published DATE,"
                + "domain VARCHAR(255) NOT NULL,"
                + "characters VARCHAR(255) NOT NULL,"
                + "style VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getCompanyTable() {
        return "CREATE TABLE companies ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "domain VARCHAR(255) NOT NULL"
                + ")";
    }
    private String getClubTable() {
        return "CREATE TABLE clubs ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "motto VARCHAR(255) NOT NULL,"
                + "photo VARCHAR(255)"
                + ")";
    }
    private String getFavouritesTable(){
        return "CREATE TABLE favourites ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getWishlistTable(){
        return "CREATE TABLE wishlist ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getReadingTable(){
        return "CREATE TABLE reading ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "user_id INT,"
                + "fiction_id INT,"
                + "graphic_id INT,"
                + "education_id INT,"
                + "FOREIGN KEY (user_id) REFERENCES users(id),"
                + "FOREIGN KEY (fiction_id) REFERENCES fiction(id),"
                + "FOREIGN KEY (graphic_id) REFERENCES graphic_novel(id),"
                + "FOREIGN KEY (education_id) REFERENCES education(id)"
                + ")";
    }
    private String getUserTable() {
        return "CREATE TABLE users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "firstname VARCHAR(255) NOT NULL," +
                "surname VARCHAR(255) NOT NULL," +
                "age INT," +
                "company_id INT," +
                "club_id INT," +
                "FOREIGN KEY (company_id) REFERENCES companies(id)," +
                "FOREIGN KEY (club_id) REFERENCES clubs(id)" +
                ")";
    }
    public static boolean isEmpty (String tableName){
        int count = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {

            String selectSql = "SELECT COUNT(*) FROM " + tableName + ";";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            resultSet.next();
            count = resultSet.getInt(1);
            System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != 0;
    }
    public static void seedCompanies(){
        if (isEmpty("companies"))
            return;

        List<String> companies = Arrays.asList("None", "Nebula Technologies", "Pulse Innovations", "Quantum Solutions", "PixelForge", "Illumine Dynamics");
        List<String> domains = Arrays.asList("", "medicine", "technology", "cyber-security", "entertainment", "green-energy");

        String statementSql = "INSERT INTO companies (name, domain) VALUES (?, ?);";
        for(int i = 0; i < companies.size(); i++){
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, companies.get(i));
                preparedStatement.setString(2, domains.get(i));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seedClubs(){
        if (isEmpty("clubs"))
            return;
        List<String> clubs = Arrays.asList(
                "None",
                "The Literary Llamas",
                "Whimsical Wordsmiths",
                "Chapter Chameleons",
                "The Prose Posse",
                "Bookworm Brigade"
        );
        List<String> mottos = Arrays.asList(
                "",
                "We read between the lines.",
                "Unleash your inner wordsmith.",
                "Blending into every chapter.",
                "Spreading the power of prose.",
                "Conquering one book at a time."
        );
        List<String> photos = Arrays.asList(
                "",
                "llama.jpg",
                "bookshelf.jpg",
                "reading-glasses.jpg",
                "book-stack.jpg",
                "bookworm.jpg"
        );

        String statementSql = "INSERT INTO clubs (name, motto, photo) VALUES (?, ?, ?);";
        for(int i = 0; i < clubs.size(); i++){
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, clubs.get(i));
                preparedStatement.setString(2, mottos.get(i));
                preparedStatement.setString(3, photos.get(i));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seedFiction(){
        if (isEmpty("fiction"))
            return;
        List<String> titles = Arrays.asList(
                "Harry Potter and the Philosopher's Stone",
                "The Da Vinci Code",
                "Pride and Prejudice",
                "The Shining",
                "The Hitchhiker's Guide to the Galaxy",
                "To Kill a Mockingbird",
                "1984",
                "The Great Gatsby",
                "The Catcher in the Rye",
                "Moby Dick",
                "Jane Eyre"
        );
        List<String> authors = Arrays.asList(
                "J.K. Rowling",
                "Dan Brown",
                "Jane Austen",
                "Stephen King",
                "Douglas Adams",
                "Harper Lee",
                "George Orwell",
                "F. Scott Fitzgerald",
                "J.D. Salinger",
                "Herman Melville",
                "Charlotte Bronte"
        );
        List<String> descriptions = Arrays.asList(
                "A boy discovers he's a wizard and attends a school of magic.",
                "A symbologist tries to uncover a conspiracy involving the Holy Grail.",
                "A love story set in Georgian England.",
                "A family becomes trapped in a haunted hotel during the winter.",
                "The misadventures of an unwitting human and his alien friend.",
                "A young girl learns about racial injustice in the Deep South.",
                "A dystopian novel set in a totalitarian society.",
                "The story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan.",
                "The story of a teenage boy in New York City.",
                "The epic tale of Captain Ahab's obsessive quest for revenge against the white whale.",
                "The story of a young governess who falls in love with her employer."
        );
        List<Date> publishedDates = Arrays.asList(
                new Date(1997, Calendar.JUNE, 26),
                new Date(2003, Calendar.MARCH, 18),
                new Date(1813, Calendar.JANUARY, 28),
                new Date(1977, Calendar.JANUARY, 28),
                new Date(1979, Calendar.OCTOBER, 12),
                new Date(1960, Calendar.JULY, 11),
                new Date(1949, Calendar.JUNE, 8),
                new Date(1925, Calendar.APRIL, 10),
                new Date(1951, Calendar.JULY, 16),
                new Date(1851, Calendar.OCTOBER, 18),
                new Date(1847, Calendar.OCTOBER, 16)
        );

        List<Boolean> availability = Arrays.asList(
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                true,
                false,
                true,
                true,
                true
        );
        List<String> charactersList = Arrays.asList(
                "Harry Potter, Hermione Granger, Ron Weasley",
                "Robert Langdon, Sophie Neveu",
                "Elizabeth Bennet, Fitzwilliam Darcy",
                "Jack Torrance, Wendy Torrance, Danny Torrance",
                "Arthur Dent, Ford Prefect",
                "Scout Finch, Atticus Finch",
                "Winston Smith, Julia",
                "Jay Gatsby, Daisy Buchanan",
                "Holden Caulfield",
                "Captain Ahab, Ishmael",
                "Jane Eyre, Edward Rochester"
        );

        List<String> allegory = Arrays.asList(
                "Friendship, Good vs. Evil, Coming of Age",
                "Religion, Art, Secret Societies",
                "Class, Marriage, Prejudice",
                "Isolation, Madness, Supernatural",
                "Absurdity, Adventure, Satire",
                "Racial Injustice, Morality",
                "Totalitarianism, Surveillance",
                "Jazz Age, Wealth, Illusion",
                "Cynicism, Alienation",
                "Obsession, Fate, Revenge",
                "Feminism, Independence, Love"
        );

        List<FictionDomain> genres = Arrays.asList(
                FictionDomain.ADVENTURE,
                FictionDomain.THRILLER,
                FictionDomain.ROMANCE,
                FictionDomain.HORROR,
                FictionDomain.COMEDY,
                FictionDomain.YOUNGADULT,
                FictionDomain.THRILLER,
                FictionDomain.ROMANCE,
                FictionDomain.YOUNGADULT,
                FictionDomain.ADVENTURE,
                FictionDomain.ROMANCE
        );
        String statementSql = "INSERT INTO fiction (title, author, description, published, available, characters, allegory, domain)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        for(int i = 0; i < titles.size(); i++){
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, titles.get(i));
                preparedStatement.setString(2, authors.get(i));
                preparedStatement.setString(3, descriptions.get(i));
                preparedStatement.setDate(4, publishedDates.get(i));
                preparedStatement.setBoolean(5, availability.get(i));
                preparedStatement.setString(6, charactersList.get(i));
                preparedStatement.setString(7, allegory.get(i));
                preparedStatement.setString(8, genres.get(i).toString());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seedEducation(){
        if (isEmpty("education"))
            return;

        List<String> titles = Arrays.asList(
                "Steve Jobs",
                "The Lean Startup",
                "Clean Code: A Handbook of Agile Software Craftsmanship",
                "Mastering the Art of French Cooking",
                "The Vegetable Gardener's Bible",
                "Born to Run: A Hidden Tribe, Superathletes, and the Greatest Race the World Has Never Seen",
                "Hands-On Machine Learning with Scikit-Learn"
        );
        List<String> authors = Arrays.asList(
                "Walter Isaacson",
                "Eric Ries",
                "Robert C. Martin",
                "Julia Child, Louisette Bertholle, and Simone Beck",
                "Edward C. Smith",
                "Christopher McDougall",
                "Geron Aurelien"
        );
        List<String> descriptions = Arrays.asList(
                "Biography of Steve Jobs, co-founder of Apple Inc.",
                "Exploring the lean startup methodology for building successful businesses.",
                "A guide to writing clean and maintainable code using agile software development principles.",
                "Classic French cookbook with detailed recipes and techniques.",
                "Comprehensive guide to vegetable gardening techniques and tips.",
                "A captivating account of a hidden tribe of superathletes and their remarkable races.",
                "Practical hands-on guide to machine learning using the Scikit-Learn library."
        );

        List<Date> publishedDates = Arrays.asList(
                new Date(2011, Calendar.OCTOBER, 24),
                new Date(2011, Calendar.SEPTEMBER, 13),
                new Date(2008, Calendar.AUGUST, 1),
                new Date(1961, Calendar.OCTOBER, 16),
                new Date(2000, Calendar.JANUARY, 1),
                new Date(2009, Calendar.MAY, 5),
                new Date(2017, Calendar.MARCH, 1)
        );
        List<Boolean> availability = Arrays.asList(
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );
        List<String> tools = Arrays.asList(
                "Apple II, Macintosh, iPod, iPhone, iPad",
                "N/A",
                "JUnit, Mockito, Jenkins, SonarQube",
                "N/A",
                "Hand pruners, Garden fork, Hose, Trowel, Garden gloves",
                "N/A",
                "N/A"
        );
        List<EducationDomain> genres = Arrays.asList(
                EducationDomain.BIOGRAPHY,
                EducationDomain.BUSINESS,
                EducationDomain.IT,
                EducationDomain.COOKING,
                EducationDomain.GARDENING,
                EducationDomain.SPORT,
                EducationDomain.IT
        );
        String statementSql = "INSERT INTO education (title, author, description, published, available, tools, domain)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        for(int i = 0; i < titles.size(); i++){
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, titles.get(i));
                preparedStatement.setString(2, authors.get(i));
                preparedStatement.setString(3, descriptions.get(i));
                preparedStatement.setDate(4, publishedDates.get(i));
                preparedStatement.setBoolean(5, availability.get(i));
                preparedStatement.setString(6, tools.get(i));
                preparedStatement.setString(7, genres.get(i).toString());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seedGraphicNovel() {
        if (isEmpty("graphic_novel"))
            return;

        List<String> titles = Arrays.asList(
                "Watchmen",
                "One Piece",
                "Maus",
                "Saga",
                "Batman: The Killing Joke",
                "Attack on Titan",
                "V for Vendetta",
                "Naruto",
                "Scott Pilgrim's Precious Little Life"
        );

        List<String> authors = Arrays.asList(
                "Alan Moore, Dave Gibbons",
                "Eiichiro Oda",
                "Art Spiegelman",
                "Brian K. Vaughan, Fiona Staples",
                "Alan Moore, Brian Bolland",
                "Hajime Isayama",
                "Alan Moore, David Lloyd",
                "Masashi Kishimoto",
                "Bryan Lee O'Malley"
        );

        List<String> descriptions = Arrays.asList(
                "A complex story of superheroes and a dark alternate history.",
                "A pirate adventure with Monkey D. Luffy and his crew.",
                "A powerful and harrowing tale of the Holocaust.",
                "An epic space opera about two star-crossed lovers.",
                "The Joker's attempt to drive Commissioner Gordon insane.",
                "Humanity fights against giant humanoid creatures.",
                "A vigilante fights against a totalitarian regime.",
                "A young ninja's journey to become the strongest ninja.",
                "A story about love, life, and video games."
        );

        List<Date> publishedDates = Arrays.asList(
                new Date(1986, Calendar.SEPTEMBER, 1),
                new Date(1997, Calendar.JULY, 22),
                new Date(1980, Calendar.JANUARY, 1),
                new Date(2012, Calendar.MARCH, 14),
                new Date(1988, Calendar.MARCH, 29),
                new Date(2009, Calendar.SEPTEMBER, 9),
                new Date(1982, Calendar.MARCH, 1),
                new Date(1999, Calendar.SEPTEMBER, 21),
                new Date(2004, Calendar.AUGUST, 18)
        );

        List<Boolean> availability = Arrays.asList(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
        );

        List<String> characters = Arrays.asList(
                "Rorschach, Dr. Manhattan, Nite Owl, Silk Spectre",
                "Monkey D. Luffy, Roronoa Zoro, Nami",
                "Art Spiegelman, Vladek Spiegelman",
                "Alana, Marko, Prince Robot IV",
                "Batman, The Joker",
                "Eren Yeager, Mikasa Ackerman, Armin Arlert",
                "V, Evey Hammond",
                "Naruto Uzumaki, Sasuke Uchiha, Sakura Haruno",
                "Scott Pilgrim, Ramona Flowers"
        );

        List<GraphicNovelDomain> domains = Arrays.asList(
                GraphicNovelDomain.COMICS,
                GraphicNovelDomain.MANGA,
                GraphicNovelDomain.COMICS,
                GraphicNovelDomain.COMICS,
                GraphicNovelDomain.COMICS,
                GraphicNovelDomain.MANGA,
                GraphicNovelDomain.COMICS,
                GraphicNovelDomain.MANGA,
                GraphicNovelDomain.COMICS
        );

        List<String> styles = Arrays.asList(
                "Color",
                "Black and White",
                "Black and White",
                "Color",
                "Color",
                "Black and White",
                "Color",
                "Black and White",
                "Black and White"
        );

        String statementSql = "INSERT INTO graphic_novel (title, author, description, published, available, characters, domain, style)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        for (int i = 0; i < titles.size(); i++) {
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, titles.get(i));
                preparedStatement.setString(2, authors.get(i));
                preparedStatement.setString(3, descriptions.get(i));
                preparedStatement.setDate(4, publishedDates.get(i));
                preparedStatement.setBoolean(5, availability.get(i));
                preparedStatement.setString(6, characters.get(i));
                preparedStatement.setString(7, domains.get(i).toString());
                preparedStatement.setString(8, styles.get(i));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void seedUsers() {
        if (isEmpty("users"))
            return;

        List<String> firstNames = Arrays.asList(
                "Căpușa",
                "Zoe",
                "Veta",
                "Doamna Cătălina",
                "Soțul",
                "Căpitanul Bălcescu",
                "Cătălina",
                "Cațavencu",
                "Doamna Cătălina",
                "Nea Puiu"
        );
        List<String> lastNames = Arrays.asList(
                "Dandanache",
                "Chiriac",
                "Farfuridi",
                "Brânzovenescu",
                "Profesorul",
                "Farfuridi",
                "Zoe",
                "Farfuridi",
                "Zoe",
                "Dandanache"
        );
        List<Integer> ages = Arrays.asList(
                50,
                28,
                35,
                40,
                45,
                55,
                30,
                42,
                38,
                60
        );
        List<String> characters = Arrays.asList(
                "Căpușa, Dandanache, Cațavencu",
                "Zoe, Chiriac, Brânzovenescu",
                "Veta, Farfuridi, Cetățeanul turmentat",
                "Doamna Cătălina, Veta",
                "Soțul, Profesorul, Veta",
                "Zoe, Profesorul, Ștefan",
                "Căpitanul Bălcescu, Farfuridi, Căpușa",
                "Cătălina, Zoe",
                "Cațavencu, Farfuridi, Ștefan",
                "Doamna Cătălina, Zoe, Cetățeanul turmentat"
        );

        List<Integer> companies = Arrays.asList(1, 3, 2, 5, 4, 1, 3, 2, 5, 4);
        List<Integer> clubs = Arrays.asList(3, 1, 4, 2, 5, 2, 4, 3, 1, 5);

        String statementSql = "INSERT INTO users (firstname, surname, age, company_id, club_id)" +
                " VALUES (?, ?, ?, ?, ?);";

        for (int i = 0; i < firstNames.size(); i++) {
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statementSql);
                preparedStatement.setString(1, firstNames.get(i));
                preparedStatement.setString(2, lastNames.get(i));
                preparedStatement.setInt(3, ages.get(i));
                preparedStatement.setInt(4, companies.get(i));
                preparedStatement.setInt(5, clubs.get(i));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
