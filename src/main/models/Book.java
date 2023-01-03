package models;

public class Book {
    protected Long id;
    protected String title;
    protected String author;
    protected int year;

    public Book() {}
    public Book(Long id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getYear() {
        return String.valueOf(this.year);
    }

}
