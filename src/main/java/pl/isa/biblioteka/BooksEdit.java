package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class BooksEdit {
    //Przemysław Wenderholm
    Scanner sc = new Scanner(System.in);
    public static List<Book> booksList = new ArrayList<>(FolderBooks.readBooks());

    public void addBook() {
        System.out.println("Podaj tytył:");
        String title = sc.nextLine();
        System.out.println("Podaj autora:");
        String author = sc.nextLine();
        System.out.println("Podaj kategorię:");
        String category = sc.nextLine();
        Book book = new Book(title,author,category,true);
        booksList.add(book);
        System.out.println("Dodano książkę: " + book.getTitle() + " autora: "+ book.getAuthor());
    }

    public boolean deleteBookByTitle() {
        System.out.println("wpisz tytul ksiazki do usuniecia");
        String title = sc.nextLine();
        return !booksList.removeIf(foundBookByTitle(title));
    }

    private static Predicate<Book> foundBookByTitle(String title) {
        return book -> book.getTitle().equalsIgnoreCase(title);
    }

    public void showAllAvailableBooks(){
        booksList.stream().filter(Book::isState)
                .forEach(el -> System.out.printf("Tytuł: %s, Autor: %s, Kategoria: %s %n",
                        el.getTitle(),
                        el.getAuthor(),
                        el.getCategory()));
    }
//TODO dodać przy wyswietlaniu wypożyczonych przez kogo została wypożyczona
    public void showAllBorrowedBooks(){
        booksList.stream().filter(book -> !book.isState())
                .forEach(el -> System.out.printf("Tytuł: %s, Autor: %s, Kategoria: %s  %n",
                        el.getTitle(),
                        el.getAuthor(),
                        el.getCategory()));
    }
    public void showAllBooks(){
        booksList.forEach(el -> System.out.printf("Tytuł: %s, Autor: %s, Kategoria: %s %n",
                el.getTitle(),
                el.getAuthor(),
                el.getCategory()));
    }

}
