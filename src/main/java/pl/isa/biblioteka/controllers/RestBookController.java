package pl.isa.biblioteka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.isa.biblioteka.dto.BookDAO;
import pl.isa.biblioteka.dto.PersonDAO;
import pl.isa.biblioteka.model.Book;
import pl.isa.biblioteka.model.User;
import pl.isa.biblioteka.repositories.BookRepository;
import pl.isa.biblioteka.servises.PersonService;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class RestBookController {

    private final BookRepository bookRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PersonDAO personDAO;
    @Autowired
    BookDAO bookDAO;

    @GetMapping("/loadusers")
    void readAndSavePerson() {
        List<User> people = PersonService.readUsers();
        for (User user : people) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_ADMIN");
            personDAO.savePerson(user);
        }
    }

    @GetMapping("/loadbooks")
    void readAndSaveBook() {
        List<Book> books = BookRepository.readBooks();
        for (Book book : books) {
            Random random = new Random();
            int r = random.nextInt(300);
            book.setCounter(r);
            bookDAO.saveBook(book);
        }
    }

    @GetMapping("/loadsampel")
    void sampelReadAndSaveBook() {
        List<Book> books = BookRepository.sampelReadBooks();
        for (Book book : books) {
            Random random = new Random();
            int r = random.nextInt(100);
            book.setCounter(r);
            System.out.println("r = " + r);
            bookDAO.saveBook(book);
        }
    }

    @GetMapping("/top2/{category}")
    public List<Book> top(@PathVariable("category") String category, Model model, Book book) {
        return bookRepository.findAllByCategoryOrderByCounterDesc(category);
    }
}