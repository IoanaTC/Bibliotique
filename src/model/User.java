package model;

public class User{
    private String firstname;
    private String surname;
    private int age;
    private Company company; //aggregation
    private Club club;

    //there is a single shelf per user -> singleton
    private Shelf shelf;

    public User(String firstname, String surname, int age, Company company, Club club) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.company = company;
        this.club = club;
    }
    public Shelf getShelf(){
        return Shelf.buildShelf(this);
    }
    public User() {
        firstname = "Unknown";
        surname = "User";
        age = -1;
        company = null;
    }
    public User(String firstname, String surname, int age, Company company,
                Shelf shelf, Club club) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.company = company;
        this.shelf = shelf;
    }
    public User(String firstname, String surname, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) { this.company = company; }

    public Club getClub() { return club; }

    public void setClub(Club club) { this.club = club; }

    public void copyConstructorShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}
