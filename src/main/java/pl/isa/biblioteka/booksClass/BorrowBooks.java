package pl.isa.biblioteka.booksClass;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BorrowBooks {
    public static Scanner scanner = new Scanner(System.in);
    public static BooksEdit booksEdit = new BooksEdit();

    public static void addBookToPerson(List<Book> personBooks) {
        List<Book> booksList = BooksEdit.booksList;
        System.out.println("Podaj tytuł książki do wypożyczenia: ");
        String bookTitle = scanner.nextLine();
        boolean findBook = false;
        for (Book book : booksList) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isState()) {
                book.setState(false);
                personBooks.add(book);
                System.out.println("Książka została wypożyczona");
                findBook = true;
                break;
            }
        }
        if (!findBook) {
            System.out.println("Nie posiadamy książki o podanym tytule");
        }
    }

    public void returnBook(List<Book> personBooks) {
        List<Book> booksList = BooksEdit.booksList;
        boolean findBook = false;
        System.out.println("Podaj tytuł książki do zwrócenia: ");
        String bookTitleToReturn = scanner.nextLine();
        for (Book personBook : personBooks) {
            if (personBook.getTitle().equalsIgnoreCase(bookTitleToReturn) && !personBook.isState()) {
                for (Book book : booksList) {
                    if (book.getTitle().equalsIgnoreCase(bookTitleToReturn)) {
                        book.setState(true);
                    }
                }
                personBooks.removeIf(foundBookByTitle(bookTitleToReturn));
                findBook = true;
                break;
            }
        }
        if (!findBook) {
            System.out.println("Użytkoniku nie posiadasz kiążki o tytule: " + bookTitleToReturn);
        }
    }

    public static void sortByCategory() {
        List<Book> books = BooksEdit.booksList.stream().filter(Book::isState).toList();
        Set<String> availableCategory = showAvailableCategory(books);
        showFilterBookByCategory(books, availableCategory);

    }

    private static Set<String> showAvailableCategory(List<Book> books) {
        Set<String> availableCategory = books.stream().map(book1 -> book1.getCategory().toLowerCase()).collect(Collectors.toSet());
        for (String category : availableCategory) {
            System.out.print("'" + category + "'  ");
        }
        return availableCategory;
    }

    private static void showFilterBookByCategory(List<Book> books, Set<String> availableCategory) {
        System.out.println();
        System.out.println("Wybierz odpowiednia kategorię: ");
        String searchCategory = scanner.nextLine();
        if (availableCategory.contains(searchCategory)) {
            List<Book> sortedBooks = books.stream().filter(book -> book.getCategory().equalsIgnoreCase(searchCategory)).toList();
            for (Book sortedBook : sortedBooks) {
                System.out.println("Książka: " + sortedBook.getTitle()
                        + " Autor: " + sortedBook.getAuthor()
                        + " Kategoria : " + sortedBook.getCategory());
            }
        } else {
            System.out.println("Brak kategorii " + searchCategory + ". Wybierz odpowiednią kategorię z listy");
        }
    }

    private static Predicate<Book> foundBookByTitle(String bookReturnTitle) {
        return book -> book.getTitle().equalsIgnoreCase(bookReturnTitle);
    }

    public void mainLoop(List<Book> personBooks) {
        System.out.println("Co chcesz zrobić");
        Option option = null;
        do {
            printMenu();
            try {
                option = chooseOption();
                executeOption(option, personBooks);
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

    private void executeOption(Option option, List<Book> personBooks) {
        switch (option) {
            case BORROW_BOOK -> addBookToPerson(personBooks);
            case RETURN_BOOK -> returnBook(personBooks);
            case SHOW_AVAILABLE_BOOK -> booksEdit.showAllAvailableBooks();
            case SHOW_BORROWED_BOOK -> personBooks.forEach(
                    (book) -> System.out.println("Tytuł: " + book.getTitle() + " Autor: " + book.getAuthor())
            );
            case SHOW_SORTED_BOOK -> sortByCategory();
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