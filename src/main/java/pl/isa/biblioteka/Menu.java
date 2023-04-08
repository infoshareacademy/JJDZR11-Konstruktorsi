package pl.isa.biblioteka;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    protected void start() {
        Users users = new Users();
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Witamy w naszej bibliotece wybierz numer menu");
            System.out.println("\nINFORMACJA TECHNICZNA do skasowania :) \n Można się zalogować jako Bibliotekarz z hasłem 0000 " +
                    "\nlub jako user test i hasło test, ale zachęcam do zakładania własnej karty ;)\n");

            System.out.println("1 - Zaloguj się");
            System.out.println("2 - Załóż kartę biblioteczną");
            System.out.println("3 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                switch (userChoose) {
                    case 1 -> {
                        scanner.skip("\n");
                        selectUser();
                    }
                    case 2 -> users.addUser();
                    case 3 -> {
                        PersonService.saveUsers();
                        FolderBooks.saveBooks();
                        System.out.println("Baza użytkowników i książek zapisana poprawnie");
                        isContinue = false;
                        System.exit(0);
                    }
                    default -> System.out.println("Został wprowadzony niewłaściwy numer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Został wprowadzony niewłaściwy znak.");
                scanner.next();
            }
        }
    }

    protected void selectUser() {
        List<Person> persons = Users.getUsers();
        System.out.println("Podaj login");
        String login = scanner.nextLine().trim();
        System.out.println("Podaj hasło");
        String password = scanner.nextLine().trim();
        boolean found = false;
        for (Person person1 : persons) {
            if (login.equalsIgnoreCase("Bibliotekarz") && password.equals("0000")) {
                librarianMenu();
            } else if (person1.getLogin().equalsIgnoreCase(login) && person1.getPassword().equals(password)) {
                found = true;
            }
        }
        if (found) {
            userMenu(login, password);
        } else {
            System.out.println("Brak użytkownika: " + login + " lub niepoprawne hasło");
        }
    }

    protected void librarianMenu() {
        Users users = new Users();
        BooksEdit booksEdit = new BooksEdit();
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1 - Widok książek");
            System.out.println("2 - Dodaj użytkownika");
            System.out.println("3 - Usuń użytkownika");
            System.out.println("4 - Lista użytkowników");
            System.out.println("5 - Dodaj książkę");
            System.out.println("6 - Usuń książkę");
            System.out.println("7 - Lista dostępnych książek");
            System.out.println("8 - Lista wypożyczonych książek książek");
            System.out.println("9 - Zmień użytkownika");
            System.out.println("10 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                switch (userChoose) {
                    case 1 -> booksEdit.showAllBooks();
                    case 2 -> users.addUser();
                    case 3 -> users.deleteUser();
                    case 4 -> users.listsUsers();
                    case 5 -> booksEdit.addBook();
                    case 6 -> booksEdit.deleteBookByTitle();
                    case 7 -> booksEdit.showAllAvailableBooks();
                    case 8 -> booksEdit.showAllBorrowedBooks();
                    case 9 -> {
                        scanner.skip("\n");
                        selectUser();
                    }
                    case 10 -> {
                        PersonService.saveUsers();
                        FolderBooks.saveBooks();
                        System.out.println("Baza użytkowników i książek zapisana poprawnie");
                        isContinue = false;
                        System.exit(0);
                    }
                    default -> System.out.println("Został wprowadzony niewłaściwy numer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Został wprowadzony niewłaściwy znak.");
                scanner.next();
            }
        }
    }

    protected void userMenu(String login, String password) {
        List<Person> users = Users.getUsers();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        boolean findUser = false;
        for (Person user : users) {
            if (login.equalsIgnoreCase(user.getLogin()) && password.equalsIgnoreCase(user.getPassword())) {
                System.out.println("Witamy Cię : " + user.getFirstName().toUpperCase() + " " + user.getFirstName().toUpperCase());
                List<Book> personBooks = user.getPersonBooks();
                boolean isContinue = true;
                while (isContinue) {
                    System.out.println("\nWybierz numer menu");
                    System.out.println("1 - Widok książek");
                    System.out.println("2 - Operacje na książkach");
                    System.out.println("3 - Zmień użytkownika");
                    System.out.println("4 - Zakończ program");
                    try {
                        int userChoose = scanner.nextInt();
                        switch (userChoose) {
                            case 1 -> booksEdit.showAllBooks();
                            case 2 -> borrowBooks.mainLoop(personBooks);
                            case 3 -> {
                                scanner.skip("\n");
                                selectUser();
                            }
                            case 4 -> {
                                PersonService.saveUsers();
                                FolderBooks.saveBooks();
                                System.out.println("Baza użytkowników i książek zapisana poprawnie");
                                isContinue = false;
                                System.exit(0);
                            }
                            default -> System.out.println("Został wprowadzony niewłaściwy numer.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Został wprowadzony niewłaściwy znak.");
                        scanner.next();
                    }
                }
            }
        }
        if (!findUser) {
            System.out.println("Brak użytkownika: " + login + " w naszej bazie");
        }
    }
}