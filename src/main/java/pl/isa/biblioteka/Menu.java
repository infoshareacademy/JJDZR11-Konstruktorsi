package pl.isa.biblioteka;

import java.util.Scanner;

public class Menu {
    public void selectUser() {
        System.out.println("\nWitamy w naszej bibliotece");
        System.out.println("Kim jesteś ?\nWybierz odpowiednią opcję w menu");
        System.out.println("1. Bibliotekarz");
        System.out.println("2. Użytkownik");
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            try {
                int userChoose = scanner.nextInt();
                if (userChoose == 1) {
                    librarianMenu();
                } else if (userChoose == 2) {
                    userMenu();
                } else {
                    System.out.println("Wprowadź poprawny numer");
                    selectUser();
                }
            } catch (Exception e) {
                System.out.println("Wprowadziłeś niepoprawny znak");
                selectUser();
            }
            isContinue = false;
        }
    }

    public void librarianMenu() {
        Users users = new Users();
        BooksEdit booksEdit = new BooksEdit();
        Scanner scanner = new Scanner(System.in);
        try {
            boolean isContinue = true;
            while (isContinue) {
                System.out.println("\nWybierz numer menu");
                System.out.println("1. Widok książek");
                System.out.println("2. Dodaj użytkownika");
                System.out.println("3. Usuń użytkownika");
                System.out.println("4. Lista użytkowników");
                System.out.println("5. Dodaj książkę");
                System.out.println("6. Usuń książkę");
                System.out.println("7. Zmień użytkownika");
                System.out.println("8. Zakończ program");
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
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println("warunek liber");
            librarianMenu();
        }
    }

    public void userMenu() {
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        Scanner scanner = new Scanner(System.in);
        try {
            boolean isContinue = true;
            while (isContinue) {
                System.out.println("\nWybierz numer menu");
                System.out.println("1. Widok książek");
                System.out.println("2. Operacje na książkach");
                System.out.println("3. Zmień użytkownika");
                System.out.println("4. Zakończ program");
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
                        }
                    }
                } else {
                    System.out.println("Wybierz poprawny numer menu");
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println("user");
            userMenu();
        }
    }
}