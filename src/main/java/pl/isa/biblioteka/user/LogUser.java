package pl.isa.biblioteka.user;

import pl.isa.biblioteka.books.Book;

import java.util.List;

public class LogUser {
    private static Person logPerson;

    public LogUser() {
    }

    public static void setLogPerson(Person logPerson) {
        LogUser.logPerson = logPerson;
    }

    public Person getLogPerson() {
        return logPerson;
    }
    public List<Book> getPersonBooks() {
        return logPerson.personBooks;
    }
}
