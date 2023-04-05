package pl.isa.biblioteka;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);

    protected void selectUser() {
        System.out.println("Witamy w naszej bibliotece\nKim jesteś ?");
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Wybierz odpowiednią opcję w menu");
            System.out.println("1 - Bibliotekarz");
            System.out.println("2 - Użytkownik");
            System.out.println("3 - Zakończ program");
            try {
                int userChoose = scanner.nextInt();
                switch (userChoose) {
                    case 1 -> librarianMenu();
                    case 2 -> {
                        scanner.nextLine();
                        System.out.println("Podaj swoje imię czytelniku:");
                        String firstName = scanner.nextLine();
                        System.out.println("Podaj swoje nazwisko czytelniku:");
                        String lastName = scanner.nextLine();
                        userMenu(firstName, lastName);
                    }
                    case 3 -> {
                        isContinue = false;
                        System.exit(0);
                    }
                    default -> System.out.println("Został wprowadzony niewłaściwy numer.\n");
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
                    case 9 -> selectUser();
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

    public void userMenu(String firstName, String lastName) {
        List<Person> users = Users.getUsers();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        boolean findUser = false;
        for (Person user : users) {
            if (firstName.equalsIgnoreCase(user.getFirstName()) && lastName.equalsIgnoreCase(user.getSecondName())) {
                System.out.println("Witamy Cię : " + firstName.toUpperCase() + " " + lastName.toUpperCase());
                List<Book> personBooks = user.getPersonBooks();
                boolean isContinue = true;
                while (isContinue) {
                    System.out.println("\nWybierz numer menu");
                    System.out.println("1. Widok książek");
                    System.out.println("2. Operacje na książkach");
                    System.out.println("3. Zmień użytkownika");
                    System.out.println("4. Zakończ program");
                    try {
                        int userChoose = scanner.nextInt();
                        switch (userChoose) {
                            case 1 -> booksEdit.showAllBooks();
                            case 2 -> borrowBooks.mainLoop(personBooks);
                            case 3 -> selectUser();
                            case 4 -> {
                                FolderBooks.saveBooks();
                                System.out.println("Baza książek zapisana poprawnie");
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
            System.out.println("Brak użytkownika o imieniu: " + firstName + " i zazwisku " + lastName + " w naszej bazie");
        }
    }
}
