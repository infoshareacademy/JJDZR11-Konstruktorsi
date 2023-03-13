package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        DANE TESTOWE DO SPRAWDZENIA DZIAŁANIA DODAWANIA I USUWANIA KSIĄŻEK Z LSTY
        Book book1 = new Book("Pan Tadeusz", "Adam Mickierwicz", "Historia", false);
        Book book2 = new Book("Pod napięciem", "Jefrey Dever", "Thriller", false);


        BooksEdit booksEdit = new BooksEdit();
//        dodanie dwóch książek
        booksEdit.addBook(book1);
        booksEdit.addBook(book2);
//        wyświetlenie listy książek
        System.out.println("lista książek po dodaniu");
        List<Book> books = booksEdit.getBooks();
        booksEdit.showBooks(books);
//        usunięcie książki po tytule

        System.out.println("podaj dokładny tytuł książki do usunięcia");
        String bookToDelete = scanner.nextLine();
//        String bookToDelete = "Pod napięciem";

        booksEdit.deleteBookByTitle(books,bookToDelete);
        System.out.println("Lista książek po usunięciu: " + bookToDelete);
        booksEdit.showBooks(books);
    }
}