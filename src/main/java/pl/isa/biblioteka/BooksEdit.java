package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BooksEdit {
    //Przemysław Wenderholm
//    TASK TO DO:
//    Jako bibliotekarz mam możliwość edycji katalogu książek, poprzez dodanie nowej książki lub usunięcie istniejącej.

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
            books.add(book);
    }

    public boolean deleteBookByTitle(List<Book> books, String title){
        return !books.removeIf(foundBookByTitle(title));
    }

    private static Predicate<Book> foundBookByTitle(String title) {
        return book -> book.getTitle().equalsIgnoreCase(title);
    }

    public List<Book> getBooks() {
        return books;
    }


}
