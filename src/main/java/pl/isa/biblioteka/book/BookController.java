package pl.isa.biblioteka.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {

    private final BookService bookService;

    List<Book> bookListByAuthor;
    List<Book> searchCategoryBook;
    List<Book> searchBook;
    List<Book> localSearchBook;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    String search(Model model, Book book){
        model.addAttribute("book", book);
        model.addAttribute("category",bookService.availableCategory());
        return "search";
    }

    @PostMapping("/filterAuthor")
    String showByAuthor(@ModelAttribute("book") Book book){
        bookListByAuthor = bookService.filterByAuthor(book.getAuthor());
        return "redirect:bookAuthorList";
    }
    @GetMapping("/bookAuthorList")
    String bookAuthList(Model model){
        model.addAttribute("books", bookListByAuthor);
        return "list";
    }
    @PostMapping("/filterTitle")
    String showByTitle(@ModelAttribute("book") Book book){
        searchBook = bookService.findBookByTitle(book.getTitle());
        return "redirect:bookByTitle";
    }

    @GetMapping("/bookByTitle")
    String bookTitle(Model model){
        model.addAttribute("books", searchBook);
        return "list";
    }

    @PostMapping("/filterCategory")
    String showByCategory(@ModelAttribute("book") Book book){
        searchCategoryBook = bookService.sortByCategory(book.getCategory());
        return "redirect:bookByCategory";
    }

    @GetMapping("/bookByCategory")
    String bookCategory(Model model){
        model.addAttribute("books", searchCategoryBook);
        return "list";
    }

    @GetMapping("/bookList")
    String bookList(Model model){
        List<Book> books = bookService.showAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "list2";
    }


    @PostMapping("/filterText")
    String showByText(@ModelAttribute("valueModel") ValueModel valueModel){
        localSearchBook = bookService.searchBookByText(valueModel.value);
        return "redirect:bookByText";
    }

    @GetMapping("/bookByText")
    String byText(Model model){
        model.addAttribute("books", localSearchBook);
        return "list";
    }
}
