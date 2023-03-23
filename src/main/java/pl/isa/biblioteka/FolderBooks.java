package pl.isa.biblioteka;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FolderBooks {
    //Kinga Biereg
    public static List<Book> readBooks() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("books1.json"));
            ObjectMapper folderBooks = new ObjectMapper();
            return Arrays.asList(folderBooks.readValue(jsonData, Book[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void saveBooks() {
        ObjectMapper mapper = new ObjectMapper();
        List<Book> booksList = BooksEdit.booksList;
        try {
            mapper.writeValue(new File("books1.json"), booksList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
