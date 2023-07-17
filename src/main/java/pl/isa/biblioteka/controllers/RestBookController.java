package pl.isa.biblioteka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.isa.biblioteka.model.Book;
import pl.isa.biblioteka.repositories.BookRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestBookController {
    private final BookRepository bookRepository;



    @GetMapping("/top2/{category}")
    public List<Book> top(@PathVariable("category")String category, Model model, Book book) {

        return bookRepository.findAllByCategoryOrderByCounterDesc(category);

    }

/*    @GetMapping("/top10/{category}")
    public List<Book> findAllByProductName(@PathVariable(value = "category") String category) {
        return bookRepository.findTop10ByCategoryOrderByCounterDesc(category);
    }*/


//    @GetMapping("/top10/{category}")
//    public ResponseEntity<List<Book>> findAllByProductName(@PathVariable(value = "category") String category) {
//        List<Book> top10Books = bookRepository.findAllByCategoryOrderByCounter(category);
//        return ResponseEntity.ok(top10Books);
//    }
//
//    @GetMapping("/top10/{category}")
//    public ResponseEntity<List<Book>> findAllByProductName(@PathVariable(value = "category") String category) {
//        PageRequest pageable = PageRequest.of(0, 10);
//        Page<Book> top10Books = bookRepository.findAllByCategoryOrderByCounterDesc(category, pageable);
//        return ResponseEntity.ok(top10Books.getContent());
//    }


}
