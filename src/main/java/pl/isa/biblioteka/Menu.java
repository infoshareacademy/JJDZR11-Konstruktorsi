package pl.isa.biblioteka;

import java.util.List;
import java.util.Scanner;

import static pl.isa.biblioteka.FolderBooks.readBooks;

public class Menu {
    public void ShowMenu() {

        AddUsers users = new AddUsers();
        Book book = new Book();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        FolderBooks folderBooks = new FolderBooks();
        List<Book> books = readBooks();


        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1. Widok książek");
            System.out.println("2. Wypożycz książkę");
            System.out.println("3. Dodaj użytkownika");
            System.out.println("4. Lista użytkowników");
            System.out.println("5. Edycja katalogu książek");
            System.out.println("6. Dodanie książki");
            System.out.println("7. usuwanie ksiązki");
            System.out.println("8. Zakończ program");


            if (scanner.hasNextInt()) {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 6) {
                    switch (userChoose) {
                        case 1 -> books.forEach(System.out::println); // add method
                        case 2 -> book.getTitle(); // add method
                        case 3 -> users.addUser(); // add method
                        case 4 -> users.listUsers(); // add method
                        case 5 -> System.out.println("Edycja katalogu książek"); // add method
                        case 6 -> booksEdit.addBook(); // add method
                        case 7 -> booksEdit.deleteBookByTitle(); // add method
                        case 8 -> {
                            System.out.println("Zakończ program");
                            isContinue = false;
                        }
                    }
                } else {
                    System.out.println("Wybierz poprawny numer menu");
                    break;
                }
            } else {
                System.out.println("Wybierz poprawny numer menu");
                break;
            }
        }
    }
}