package pl.isa.biblioteka;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {



        Book book1 = new Book("Ksiazka1", "Adam", "Horror", true);
        Book book2 = new Book("Ksiazka2", "Roman", "Komedia", true);
        Book book3 = new Book("Ksiazka3", "Andrzej", "Dramat", true);
        Book book4 = new Book("Ksiazka4", "Kamil", "Komedia", true);


        Person person1 = new Person("Jan", "Kowalski");
        Person person2 = new Person("Adam", "Nowak");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        System.out.println("Lista książek");
        showBooks(books);
        System.out.println();

        System.out.println("osoby przed dodaniem ksiązki");
        showPersons(personList);
        System.out.println();

//        Scanner sc = new Scanner(System.in);
//        System.out.println("dokładny tytuł do wypozyczenia:");
//        String bookTitle = sc.nextLine();

//        tytuły książek do zwrotu
        String bookTitle1 = "ksiazka1";
        String bookTitle2 = "ksiazka2";

        BorrowBooks borBook = new BorrowBooks();
        borBook.addBookToPerson(books, person1, bookTitle1);
        borBook.addBookToPerson(books, person1, bookTitle2);

        System.out.println("osoby po oddaniu ksiązki");
        showPersons(personList);
        System.out.println();

        System.out.println("dostępne książki po wypożyczeniu");
        availableBook(books, borBook);
//        System.out.println("tytul ksiazki do zwrotu");
//        String bookReturnTitle = sc.nextLine();

//        oddanie pierwszej ksiązki
        String bookReturnTitle1 = "ksiazka1";
        borBook.returnBook(person1, bookReturnTitle1);

        System.out.println("ksiazki uzytkowników po oddaniu pierwszej ksiazki");
        showPersons(personList);

        System.out.println("dostępne ksiazki po zwrocie pierwszej ksiazki-----------");
        availableBook(books, borBook);

        String bookReturnTitle2 = "ksiazka2";
        System.out.println("ostępne ksiazki po zwrocie drugiej ksiazki-----------");
        borBook.returnBook(person1, bookReturnTitle2);
        availableBook(books, borBook);
    }

    private static void showBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void showPersons(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    private static void availableBook(List<Book> books, BorrowBooks borBook) {
        List<Book> availableBooks = borBook.showAvailableBooks(books);
        showBooks(availableBooks);
    }
}