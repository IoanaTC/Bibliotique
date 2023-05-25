package service;

import config.DatabaseConfiguration;
import model.Book;
import model.Education;
import model.Fiction;
import model.GraphicNovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookService {
    private static int fictionIndex;
    private static int graphicIndex;
    private static int educationIndex;

    public static void showAllBooks() {
        System.out.println("These are the available books for choosing:");
        int index = 1;

        System.out.println("These are all the Fiction books:");
        ArrayList<Book> fiction = getAllFiction();
        if (!fiction.isEmpty()) {
            for (Book book : fiction) {
                System.out.printf("%d. %s - %s\n", index++, book.getTitle(), book.getAuthor());
            }
        } else System.out.println("There are no Fiction books on the store");
        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println("These are all the Graphic Novel books:");
        ArrayList<Book> graphic = getAllGraphic();
        if (!graphic.isEmpty()) {
            for (Book book : graphic) {
                System.out.printf("%d. %s - %s\n", index++, book.getTitle(), book.getAuthor());
            }
        } else System.out.println("There are no Graphic Novel books on the store");
        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println("These are all the Education books:");
        ArrayList<Book> education = getAllEducation();
        if (!education.isEmpty()) {
            for (Book book : education) {
                System.out.printf("%d. %s - %s\n", index++, book.getTitle(), book.getAuthor());
            }
        } else System.out.println("There are no Education books on the store");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static ArrayList<Book> getBooks() {

        ArrayList<Book> books = new ArrayList<Book>(getAllFiction());
        fictionIndex = books.size();

        books.addAll(getAllGraphic());
        graphicIndex = books.size();

        books.addAll(getAllEducation());
        educationIndex = books.size();

        return books;
    }

    private static ArrayList<Book> getAllFiction() {
        ArrayList<Book> books = new ArrayList<Book>();
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, characters, allegory" +
                    " FROM fiction;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Fiction fiction = new Fiction();

                fiction.setTitle(resultSet.getString(1));
                fiction.setAuthor(resultSet.getString(2));
                fiction.setDescription(resultSet.getString(3));
                fiction.setAvailable(resultSet.getBoolean(4));
                fiction.setPublished(resultSet.getDate(5));
                fiction.setDomain(resultSet.getString(6));
                fiction.setCharacters(resultSet.getString(7));
                fiction.setAllegory(resultSet.getString(8));

                books.add(fiction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private static ArrayList<Book> getAllGraphic() {
        ArrayList<Book> books = new ArrayList<Book>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, characters, style" +
                    " FROM graphic_novel;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GraphicNovel graphic = new GraphicNovel();

                graphic.setTitle(resultSet.getString(1));
                graphic.setAuthor(resultSet.getString(2));
                graphic.setDescription(resultSet.getString(3));
                graphic.setAvailable(resultSet.getBoolean(4));
                graphic.setPublished(resultSet.getDate(5));
                graphic.setDomain(resultSet.getString(6));
                graphic.setCharacters(resultSet.getString(7));
                graphic.setStyle(resultSet.getString(8));

                books.add(graphic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private static ArrayList<Book> getAllEducation() {
        ArrayList<Book> books = new ArrayList<Book>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, tools" +
                    " FROM education;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Education education = new Education();

                education.setTitle(resultSet.getString(1));
                education.setAuthor(resultSet.getString(2));
                education.setDescription(resultSet.getString(3));
                education.setAvailable(resultSet.getBoolean(4));
                education.setPublished(resultSet.getDate(5));
                education.setDomain(resultSet.getString(6));
                education.setTools(resultSet.getString(7));

                books.add(education);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Fiction getFictionById(int fiction_id) {
        Fiction book = new Fiction();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, characters, allegory" +
                    " FROM fiction WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, fiction_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            book.setTitle(resultSet.getString(1));
            book.setAuthor(resultSet.getString(2));
            book.setDescription(resultSet.getString(3));
            book.setAvailable(resultSet.getBoolean(4));
            book.setPublished(resultSet.getDate(5));
            book.setDomain(resultSet.getString(6));
            book.setCharacters(resultSet.getString(7));
            book.setAllegory(resultSet.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public static int getFictionId(Fiction fiction){
        int index = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT id FROM fiction WHERE title = ? and author = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, fiction.getTitle());
            preparedStatement.setString(2, fiction.getAuthor());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            index = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
    public static GraphicNovel getGraphicById(int graphic_id) {
        GraphicNovel book = new GraphicNovel();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, characters, style" +
                    " FROM graphic_novel WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, graphic_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            book.setTitle(resultSet.getString(1));
            book.setAuthor(resultSet.getString(2));
            book.setDescription(resultSet.getString(3));
            book.setAvailable(resultSet.getBoolean(4));
            book.setPublished(resultSet.getDate(5));
            book.setDomain(resultSet.getString(6));
            book.setCharacters(resultSet.getString(7));
            book.setStyle(resultSet.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public static int getGraphicId(GraphicNovel graphicNovel){
        int index = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT id FROM graphic_novel WHERE title = ? and author = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, graphicNovel.getTitle());
            preparedStatement.setString(2, graphicNovel.getAuthor());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            index = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
    public static Education getEducationById(int education_id) {
        Education book = new Education();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT title, author, description, available, published, domain, tools" +
                    " FROM education WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, education_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            book.setTitle(resultSet.getString(1));
            book.setAuthor(resultSet.getString(2));
            book.setDescription(resultSet.getString(3));
            book.setAvailable(resultSet.getBoolean(4));
            book.setPublished(resultSet.getDate(5));
            book.setDomain(resultSet.getString(6));
            book.setTools(resultSet.getString(7));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public static int getEducationId(Education education){
        int index = 0;
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            String selectSql = "SELECT id FROM education WHERE title = ? and author = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, education.getTitle());
            preparedStatement.setString(2, education.getAuthor());

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            index = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
    public static int getFictionIndex() { return fictionIndex;}
    public static int getGraphicIndex() { return graphicIndex; }
    public static int getEducationIndex() { return educationIndex; }
}
