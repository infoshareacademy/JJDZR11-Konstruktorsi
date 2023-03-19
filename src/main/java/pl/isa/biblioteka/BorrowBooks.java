package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BorrowBooks {
    //wszyscy
//    Jako użytkownik chciałbym mieć możliwość wypożyczenia książki. W katalogu książek, mogę wybrać książkę do
//        wypożyczenia i mogę zwrócić wypożyczoną książkę. Książek raz wypożyczoną, nie można wypożyczyć,
//        a książkę dostępną w katalogu nie można zwrócić.

    public static void main(String[] args) {

        Book book1 = new Book("Ksiazka1", "Adam", "Horror", true);
        Book book2 = new Book("Ksiazka2", "Roman", "Komedia", true);
        Book book3 = new Book("Ksiazka3", "Andrzej", "Dramat", true);
        Book book4 = new Book("Ksiazka4", "Kamil", "Komedia", true);


        Person person1 = new Person("Jan", "Kowalski");
        Person person2 = new Person("Adam", "Nowak");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        System.out.println("Lista książek");
        showBooks(books);
        System.out.println();

        System.out.println("osoby przed dodaniem ksiązki");
        showPersons(personList);
        System.out.println();

//        Scanner sc = new Scanner(System.in);
//        System.out.println("dokładny tytuł do wypozyczenia:");
//        String bookTitle = sc.nextLine();

//        tytuły książek do zwrotu
        String bookTitle1 = "ksiazka1";
        String bookTitle2 = "ksiazka2";

        BorrowBooks borBook = new BorrowBooks();
        borBook.addBookToPerson(books, person1, bookTitle1);
        borBook.addBookToPerson(books, person1, bookTitle2);

        System.out.println("osoby po oddaniu ksiązki");
        showPersons(personList);
        System.out.println();

        System.out.println("dostępne książki po wypożyczeniu");
        availableBook(books, borBook);
//        System.out.println("tytul ksiazki do zwrotu");
//        String bookReturnTitle = sc.nextLine();

//        oddanie pierwszej ksiązki
        String bookReturnTitle1 = "ksiazka1";
        borBook.returnBook(person1, bookReturnTitle1);

        System.out.println("ksiazki uzytkowników po oddaniu pierwszej ksiazki");
        showPersons(personList);

        System.out.println("dostępne ksiazki po zwrocie pierwszej ksiazki-----------");
        availableBook(books, borBook);

        String bookReturnTitle2 = "ksiazka2";
        System.out.println("ostępne ksiazki po zwrocie drugiej ksiazki-----------");
        borBook.returnBook(person1, bookReturnTitle2);
        availableBook(books, borBook);
    }


    public void addBookToPerson(List<Book> books, Person person1, String bookTitle) {
        for (Book book : books) {
            if(book.getTitle().equalsIgnoreCase(bookTitle) && book.isState()){
                book.setState(false);
                person1.books.add(book);
            }
        }
    }
    public List<Book> showAvailableBooks(List<Book> books){
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.isState()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public void returnBook(Person person, String bookReturnTitle) {
        List<Book> personBooks = person.getBooks();
        for (Book personBook : personBooks) {
            if(personBook.getTitle().equalsIgnoreCase(bookReturnTitle) && !personBook.isState()){
                personBook.setState(true);
                personBooks.removeIf(foundBookByTitle(bookReturnTitle));
                break;
            }
        }
    }

    private static Predicate<Book> foundBookByTitle(String bookReturnTitle) {
        return book -> book.getTitle().equalsIgnoreCase(bookReturnTitle);
    }

    private static void showBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void showPersons(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    private static void availableBook(List<Book> books, BorrowBooks borBook) {
        List<Book> availableBooks = borBook.showAvailableBooks(books);
        showBooks(availableBooks);
    }
}
