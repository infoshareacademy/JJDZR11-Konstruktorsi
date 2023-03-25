package pl.isa.biblioteka;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class BorrowBooks {
    public static Scanner scanner = new Scanner(System.in);
    public static BooksEdit booksEdit = new BooksEdit();


    public static void addBookToPerson() {
        //TODO dodanie komunikatu jezeli nie ma takiego użutkownika oraz jezeli nie masz szukanej książki
        List<Book> booksList = BooksEdit.booksList;
        List<Person> users = AddUsers.getUsers();
        String firstName = getFirstName();
        String lastName = getLastName();
        for (Person user : users) {
            if (firstName.equalsIgnoreCase(user.getFirstName()) && lastName.equalsIgnoreCase(user.getSecondName())) {
                System.out.println("Mamy Cię w naszej bazie ;)");
                System.out.println("Podaj tytuł książki do wypożyczenia: ");
                String bookTitle = scanner.nextLine();
                for (Book book : booksList) {
                    if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isState()) {
                        book.setState(false);
                        user.personBooks.add(book);
                        System.out.println("Książka została wypożyczona");
                    }
                }
            }
        }
    }

    public void returnBook() {
        List<Person> users = AddUsers.getUsers();
        String firstName = getFirstName();
        String lastName = getLastName();
        for (Person user : users) {
            if (firstName.equalsIgnoreCase(user.getFirstName()) && lastName.equalsIgnoreCase(user.getSecondName())) {
                System.out.println("Mamy Cię w naszej bazie ;)");
                List<Book> personBooks = user.getPersonBooks();
                System.out.println("Podaj tytuł książki do zwrócenia: ");
                String bookTitleToReturn = scanner.nextLine();
                for (Book personBook : personBooks) {
                    if (personBook.getTitle().equalsIgnoreCase(bookTitleToReturn) && !personBook.isState()) {
                        personBook.setState(true);
                        personBooks.removeIf(foundBookByTitle(bookTitleToReturn));
                        break;
                    }
                }

            }
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

    private static Predicate<Book> foundBookByTitle(String bookReturnTitle) {
        return book -> book.getTitle().equalsIgnoreCase(bookReturnTitle);
    }

    public void mainLoop() {
        System.out.println("Co chcesz zrobić");
        Option option = null;
        do {
            printMenu();
            try {
                option = chooseOption();
                executeOption(option);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (option != Option.EXIT);
    }

    private Option chooseOption() {
        int option = scanner.nextInt();
        scanner.nextLine();
        return Option.fromInt(option);
    }

    private void executeOption(Option option) {
        switch (option) {
            case BORROW_BOOK -> addBookToPerson();
            case RETURN_BOOK -> returnBook();
            case SHOW_AVAILABLE_BOOK -> booksEdit.showAllAvailableBooks();
            case SHOW_BORROWED_BOOK -> booksEdit.showAllBorrowedBooks();

            //TODO dodanie opcji sortowania listy po kategorii
            //TODO dodanie opcji wyszukania książki po nazwie
           //TODO dostosowanie menu głownego dla użytkownika oraz bibliotekarza np
//            zarządzanie wypożyczeniami - dla bibliotekarza oraz osoby
//            zarządzanie osobami(dodawanie, przegladanie, usuwanie) - dla bibliotekarza
//            zarządzanie książkami(dodawnie, przegladanie, usuwanie) - dla bibliotekarza
            //TODO - pokazanie starystyk wypożyczających (osoba wypożyczająca - ile książek)
            case EXIT -> close();
        }
    }

    private void close() {
        System.out.println("Bye Bye!");
    }

    private void printMenu() {
        System.out.println("Wybierz opcję:");
        for (Option option : Option.values()) {
            System.out.println(option.toString());
        }
    }

    private static enum Option {
        BORROW_BOOK(1, "Wypożycz książkę"),
        RETURN_BOOK(2, "Oddaj książkę"),
        SHOW_AVAILABLE_BOOK(3, "Zobacz dostępne książki"),
        SHOW_BORROWED_BOOK(4, "Pokaż wypożyczone książki"),
        EXIT(5, "Wróć do głównego menu");

        private final int optionNumber;
        private final String description;

        Option(int optionNumber, String description) {
            this.optionNumber = optionNumber;
            this.description = description;
        }

        static Option fromInt(int option) {
            if (option < 0 || option > values().length) {
                throw new IllegalArgumentException("Opcja o takim numerze nie istnieje");
            }
            return values()[option - 1];
        }

        @Override
        public String toString() {
            return String.format("%d - %s", optionNumber, description);
        }
    }


}
