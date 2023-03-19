package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BorrowBooks {
    //wszyscy
//    Jako użytkownik chciałbym mieć możliwość wypożyczenia książki. W katalogu książek, mogę wybrać książkę do
//        wypożyczenia i mogę zwrócić wypożyczoną książkę. Książek raz wypożyczoną, nie można wypożyczyć,
//        a książkę dostępną w katalogu nie można zwrócić.

    public void addBookToPerson(List<Book> books, Person person1, String bookTitle) {
        for (Book book : books) {
            if(book.getTitle().equalsIgnoreCase(bookTitle) && book.isState()){
                book.setState(false);
                person1.books.add(book);
            }
        }
    }
    public List<Book> showAvailableBooks(List<Book> books){
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.isState()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public void returnBook(Person person, String bookReturnTitle) {
        List<Book> personBooks = person.getBooks();
        for (Book personBook : personBooks) {
            if(personBook.getTitle().equalsIgnoreCase(bookReturnTitle) && !personBook.isState()){
                personBook.setState(true);
                personBooks.removeIf(foundBookByTitle(bookReturnTitle));
                break;
            }
        }
    }

    private static Predicate<Book> foundBookByTitle(String bookReturnTitle) {
        return book -> book.getTitle().equalsIgnoreCase(bookReturnTitle);
    }
}
