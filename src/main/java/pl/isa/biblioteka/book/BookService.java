package pl.isa.biblioteka.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.isa.biblioteka.file.FolderBooks;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookService {
    public static Scanner sc = new Scanner(System.in);
    public static List<Book> booksList = new ArrayList<>(FolderBooks.readBooks());

    public void addBook() {
        System.out.println("Podaj tytuł:");
        String title = sc.nextLine();
        System.out.println("Podaj autora:");
        String author = sc.nextLine();
        System.out.println("Podaj kategorię:");
        String category = sc.nextLine();
        Book book = new Book(title, author, category, true);
        booksList.add(book);
        System.out.println("Dodano książkę: " + book.getTitle() + " autora: " + book.getAuthor());
    }

    public void deleteBookByTitle() {
        System.out.println("wpisz tytul ksiazki do usuniecia");
        String title = sc.nextLine();
        boolean findBook = false;
        for (Book book : booksList) {
            if (foundBookByTitle(title).test(book)) {
                booksList.removeIf(foundBookByTitle(title));
                findBook = true;
                break;
            }
        }
        if (!findBook) {
            System.out.println("W naszej bazie nie ma takiej książki o tytule: " + title);
        }
    }

    private static Predicate<Book> foundBookByTitle(String title) {
        return book -> book.getTitle().equalsIgnoreCase(title);
    }

    public List<Book> showAllAvailableBooks() {
        return booksList.stream().filter(Book::isState).toList();
    }

    public void showAllBorrowedBooks() {
        booksList.stream().filter(book -> !book.isState())
                .forEach(el -> System.out.printf("Tytuł: %s, Autor: %s, Kategoria: %s  %n",
                        el.getTitle(),
                        el.getAuthor(),
                        el.getCategory()));
    }

    public List<Book> showAllBooks() {
        return booksList;
    }

    public List<Book> findBookByTitle(String title) {
       List<Book> searchBook;
        Set<String> collect = booksList.stream().map(book -> book.getTitle().toLowerCase()).collect(Collectors.toSet());
        String searchTitle = title.toLowerCase();
        if (collect.contains(searchTitle)) {
            searchBook = booksList.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).toList();
        } else {
            searchBook = Collections.emptyList();
        }
        return searchBook;
    }


    public List<Book> filterByAuthor(String author) {
        List<Book> books = booksList.stream().filter(Book::isState).toList();
        Set<String> authorList = books.stream().map(book2 -> book2.getAuthor().toLowerCase()).collect(Collectors.toSet());
        List<Book> searchedBooks;
        String searchAuthor = author.toLowerCase();
        if (authorList.contains(searchAuthor)) {
            searchedBooks = books.stream().filter(book3 -> book3.getAuthor().equalsIgnoreCase(searchAuthor)).toList();

        } else {
            searchedBooks = Collections.emptyList();
        }
        return searchedBooks;
    }

    public List<Book> sortByCategory(String category) {
        List<Book> books = booksList.stream().filter(Book::isState).toList();
        Set<String> availableCategory = availableCategory();
        List<Book> sortedBook;
        if (availableCategory.contains(category)) {
             sortedBook = books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
        } else {
            sortedBook = Collections.emptyList();
        }
        return sortedBook;
    }

    public Set<String> availableCategory() {
        Set<String> availableCategory = booksList.stream().map(book1 -> book1.getCategory().toLowerCase()).collect(Collectors.toSet());
        return availableCategory;
    }


    public Page<Book> findPaginated(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;
        if(booksList.size()< startItem){
            list = Collections.emptyList();
        }else {
            int toIndex= Math.min(startItem + pageSize, booksList.size());
            list = booksList.subList(startItem,toIndex);
        }
        Page<Book> bookPage = new PageImpl<>(list, PageRequest.of(currentPage,pageSize),booksList.size());
        return bookPage;
    }

    public List<Book> searchBookByText(String text){
        List<Book> findBook = booksList.stream().filter(book -> book.getTitle().contains(text)
                        || book.getAuthor().contains(text))
                .collect(Collectors.toList());
        return findBook;
    }


}
