package pl.isa.biblioteka.book;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/bookList")
    String bookList(Model model){
        List<Book> books = bookRepository.showAllBooks();
        model.addAttribute("books", books);
        return "list";
    }
}
