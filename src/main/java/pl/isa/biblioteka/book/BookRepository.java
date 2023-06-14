package pl.isa.biblioteka.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class BookRepository {

    public static void saveBooks() {
        ObjectMapper mapper = new ObjectMapper();
        List<Book> booksList = BookService.booksList;
        try {
            mapper.writeValue(new File("booksFile.json"), booksList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}