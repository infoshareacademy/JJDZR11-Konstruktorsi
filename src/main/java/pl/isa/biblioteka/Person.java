package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Integer id;
    private String firstName;
    private String secondName;

    public List<Book> personBooks = new ArrayList<>();

    public Person() {
    }
    public Person(String firstName, String secondName, Integer id){
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    public Person(String firstName, String secondName, List<Book> personBooks, Integer id) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.personBooks = personBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Book> getPersonBooks() {
        return personBooks;
    }

    public void showPersonBooks() {
        for (Book personBook : personBooks) {
            System.out.printf("Tytuł: %s, Autor: %s %n", personBook.getTitle(), personBook.getAuthor());
        }
    }

    public void setPersonBooks(List<Book> personBooks) {
        this.personBooks = personBooks;
    }

    public void addBookToPersonList(Book book) {
        personBooks.add(book);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", personBooks=" + personBooks +
                '}';
    }
}