package pl.isa.biblioteka.menu;

import pl.isa.biblioteka.user.LogUser;
import pl.isa.biblioteka.user.Person;

import java.util.Scanner;

import static pl.isa.biblioteka.books.BorrowBooks.*;

public class BooksBorrowMenu {
    public static Scanner scanner = new Scanner(System.in);

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
        LogUser logUser = new LogUser();
        switch (option) {
            case BORROW_BOOK -> borrowBookMethod();

            case RETURN_BOOK -> {
                if (returnBook()) {
                    System.out.println("Książka została zwrócona");
                } else {
                    System.out.println("Użytkoniku nie posiadasz takiej kiążki");
                }
                ;
            }
            case SHOW_AVAILABLE_BOOK -> booksEdit.showAllAvailableBooks();
            case SHOW_BORROWED_BOOK -> {
                if (!logUser.getPersonBooks().isEmpty()) {
                    logUser.getPersonBooks().forEach(
                            (book) -> System.out.println("Tytuł: " + book.getTitle() + " Autor: " + book.getAuthor())
                    );
                } else {
                    System.out.println("Nie wypożyczono jeszcze żadnej książki");
                }
            }
            case SHOW_SORTED_BOOK -> sortByCategory();
            case EXIT -> close();
        }
    }

    private static void borrowBookMethod() {
        System.out.println("Podaj tytuł książki do wypożyczenia: ");
        String bookTitle = scanner.nextLine();
        if ((addBookToPerson(bookTitle))) {
            System.out.println("Książka została wypożyczona");
        } else {
            System.out.println("Nie posiadamy książki o podanym tytule");
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

    private enum Option {
        BORROW_BOOK(1, "Wypożycz książkę"),
        RETURN_BOOK(2, "Oddaj książkę"),
        SHOW_AVAILABLE_BOOK(3, "Zobacz dostępne książki"),
        SHOW_BORROWED_BOOK(4, "Pokaż moje wypożyczone książki"),
        SHOW_SORTED_BOOK(5, "Wyszukaj dostępne książki po kategorii"),
        EXIT(6, "Wróć do głównego menu");

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
