package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksEdit {
    //Przemysław Wenderholm
//    TASK TO DO:
//    Jako bibliotekarz mam możliwość edycji katalogu książek, poprzez dodanie nowej książki lub usunięcie istniejącej.

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
            books.add(book);
    }

    public void deleteBookByTitle(List<Book> books, String title){
        if (!books.removeIf(book -> book.getTitle().equalsIgnoreCase(title))) {
            System.out.println("nie ma takiej książki ");
        }
    }

    public List<Book> getBooks() {
        return books;
    }

}
