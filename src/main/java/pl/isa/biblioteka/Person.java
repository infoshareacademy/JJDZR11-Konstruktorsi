package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Person {
    private String firstName;
    private String secondName;

    public List<Book> books = new ArrayList<>() ;

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Person(String firstName, String secondName, List<Book> books) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.books = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", books=" + books +
                '}';
    }
}
