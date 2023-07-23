package pl.isa.biblioteka.mappers;

import org.springframework.stereotype.Service;
import pl.isa.biblioteka.dto.BookDTO;
import pl.isa.biblioteka.model.Book;

@Service
public class BookMaper {

    public BookDTO toDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .borrowingDate(book.getBorrowingDate())
                .returnDate(book.getReturnDate())
                .counter(book.getCounter())
                .build();
    }
}