package pl.isa.biblioteka;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
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
//                TODO przekazanie użtykownika do user menu
                String firstName = getFirstName();
                String lastName = getLastName();
                userMenu(firstName,lastName);
            }
        } catch (Exception e) {
            System.out.println("Wprowadź poprawny numer menu");
        }
    }

    private static String getLastName() {
        System.out.println("Podaj swoje nazwisko czytelniku:");
        return scanner.nextLine();
    }

    private static String getFirstName() {
        System.out.println("Podaj swoje imię czytelniku:");
        return scanner.nextLine();
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
            System.out.println("6. Usuń książkę");
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
    public void userMenu(String firstName, String lastName) {
        List<Person> users = Users.getUsers();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        boolean findUser = false;
        for (Person user : users) {
            if (firstName.equalsIgnoreCase(user.getFirstName()) && lastName.equalsIgnoreCase(user.getSecondName())) {
                System.out.println("Witamy Cię : " + firstName + " " + lastName);
                List<Book> personBooks = user.getPersonBooks();
                boolean isContinue = true;
                while (isContinue) {
                    System.out.println("\nWybierz numer menu");
                    System.out.println("1. Widok książek");
                    System.out.println("2. Operacje na książkach");
                    System.out.println("3. Zmień użytkownika");
                    System.out.println("4. Zakończ program");
                    if (scanner.hasNextInt()) {
                        int userChoose = scanner.nextInt();
                        if (userChoose > 0 && userChoose < 9) {
                            switch (userChoose) {
                                case 1 -> booksEdit.showAllBooks();
                                case 2 -> borrowBooks.mainLoop(personBooks);
                                case 3 -> selectUser();
                                case 4 -> {
                                    PersonService.saveUsers();
                                    FolderBooks.saveBooks();
                                    System.out.println("Baza użytkowników i książek zapisana poprawnie");
                                    findUser = true;
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
        if (!findUser) {
            System.out.println("Brak Użytkownika " + firstName + " " + lastName + " w naszej bazie");
        }
    }
}