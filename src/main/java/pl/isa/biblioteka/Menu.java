package pl.isa.biblioteka;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    protected void selectUser() {
        System.out.println("Witamy w naszej bibliotece\nKim jesteś ?");
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Wybierz odpowiednią opcję w menu");
            System.out.println("1 - Bibliotekarz");
            System.out.println("2 - Użytkownik");
            System.out.println("3 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                if (userChoose == 1) {
                    librarianMenu();
                } else if (userChoose == 2) {
                    userMenu();
                } else if (userChoose == 3) {
                    isContinue = false;
                } else {
                    System.out.println("Został wprowadzony niewłaściwy numer\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Został wprowadzony niewłaściwy znak\n");
                scanner.next();
            }
        }
    }

    protected void librarianMenu() {
        Users users = new Users();
        BooksEdit booksEdit = new BooksEdit();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1 - Widok książek");
            System.out.println("2 - Dodaj użytkownika");
            System.out.println("3 - Usuń użytkownika");
            System.out.println("4 - Lista użytkowników");
            System.out.println("5 - Dodaj książkę");
            System.out.println("6 - Usuń książkę");
            System.out.println("7 - Zmień użytkownika");
            System.out.println("8 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 9) {
                    switch (userChoose) {
                        case 1 -> booksEdit.showAllBooks();
                        case 2 -> users.addUser();
                        case 3 -> users.deleteUser();
                        case 4 -> users.listsUsers();
                        case 5 -> booksEdit.addBook();
                        case 6 -> booksEdit.deleteBookByTitle();
                        case 7 -> selectUser();
                        case 8 -> {
                            PersonService.saveUsers();
                            FolderBooks.saveBooks();
                            System.out.println("Baza użytkowników i książek zapisana poprawnie");
                            isContinue = false;
                            System.exit(0);
                        }
                    }
                } else {
                    System.out.println("Został wprowadzony niewłaściwy numer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Został wprowadzony niewłaściwy znak.");
                scanner.next();
            }
        }
    }

    protected void userMenu() {
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1 - Widok książek");
            System.out.println("2 - Operacje na książkach");
            System.out.println("3 - Zmień użytkownika");
            System.out.println("4 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 5) {
                    switch (userChoose) {
                        case 1 -> booksEdit.showAllBooks();
                        case 2 -> borrowBooks.mainLoop();
                        case 3 -> selectUser();
                        case 4 -> {
                            FolderBooks.saveBooks();
                            System.out.println("Baza książek zapisana poprawnie");
                            isContinue = false;
                            System.exit(0);
                        }
                    }
                } else {
                    System.out.println("Został wprowadzony niewłaściwy numer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Został wprowadzony niewłaściwy znak.");
                scanner.next();
            }
        }
    }
}