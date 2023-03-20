package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String secondName;

    public List<Book> personBooks = new ArrayList<>() ;


    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Person(String firstName, String secondName, List<Book> personBooks) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.personBooks = personBooks;
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

    public void showPersonBooks(){
        for (Book personBook : personBooks) {
            System.out.printf("Tytuł: %s, Autor: %s %n", personBook.getTitle(),personBook.getAuthor());
        }
    }
    public void setPersonBooks(List<Book> personBooks) {
        this.personBooks = personBooks;
    }

    public void addBookToPersonList(Book book){
        personBooks.add(book);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", books=" + personBooks +
                '}';
    }
}
