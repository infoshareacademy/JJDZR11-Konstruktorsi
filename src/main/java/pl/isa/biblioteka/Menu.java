package pl.isa.biblioteka;

import java.util.Scanner;

public class Menu {
    public void selectUser() {
        System.out.println("\nWitamy w naszej bibliotece");
        System.out.println("Kim jesteś ?\nWybierz odpowiednią opcję w menu");
        System.out.println("1. Bibliotekarz");
        System.out.println("2. Użytkownik");
        Scanner scanner = new Scanner(System.in);
        try {
            int userChoose = scanner.nextInt();
            if (userChoose == 1) {
                librarianMenu();
            } else if (userChoose == 2) {
                userMenu();
                System.out.println("Wybierz poprawny numer menu");
            }
        } catch (Exception e) {
            System.out.println("Wprowadź poprawny numer menu");
        }
    }

    public void librarianMenu() {
        Users users = new Users();
        BooksEdit booksEdit = new BooksEdit();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1. Widok książek");
            System.out.println("2. Dodaj użytkownika");
            System.out.println("3. Usuń użytkownika");
            System.out.println("4. Lista użytkowników");
            System.out.println("5. Dodaj książkę");
            System.out.println("6. Usuń ksiązki");
            System.out.println("7. Zmień użytkownika");
            System.out.println("8. Zakończ program");
            if (scanner.hasNextInt()) {
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

    public void userMenu() {
        Users users = new Users();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1. Widok książek");
            System.out.println("2. Operacje na książkach");
            System.out.println("3. Lista użytkowników");
            System.out.println("4. Zmień użytkownika");
            System.out.println("5. Zakończ program");
            if (scanner.hasNextInt()) {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 9) {
                    switch (userChoose) {
                        case 1 -> booksEdit.showAllBooks();
                        case 2 -> borrowBooks.mainLoop();
                        case 3 -> users.listsUsers();
                        case 4 -> selectUser();
                        case 5 -> {
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