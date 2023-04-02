package pl.isa.biblioteka;

import java.util.List;
import java.util.Scanner;

import static pl.isa.biblioteka.FolderBooks.readBooks;

public class Menu {
    public void ShowMenu() {

        Users users = new Users();
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
            System.out.println("4. Usuwanie użytkownika");
            System.out.println("5. Lista użytkowników");
            System.out.println("6. Dodanie książki");
            System.out.println("7. Usuwanie ksiązki");
            System.out.println("8. Zakończ program");


            if (scanner.hasNextInt()) {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 9) {
                    switch (userChoose) {
                        case 1 -> booksEdit.showAllBooks(); // add method
                        case 2 -> borrowBooks.mainLoop(); // add method
                        case 3 -> users.addUser(); // add method
                        case 4 -> users.deleteUser();
                        case 5 -> users.listsUsers(); // add method
                        case 6 -> booksEdit.addBook(); // add method
                        case 7 -> booksEdit.deleteBookByTitle(); // add method
                        //TODO dodanie opcji wypisania pojedyncczego użytkownika
                        case 8 -> {
                            PersonService.saveUsers();
                            FolderBooks.saveBooks();
                            System.out.println("Baza użytkowników i książek zapisana poprawnie");
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