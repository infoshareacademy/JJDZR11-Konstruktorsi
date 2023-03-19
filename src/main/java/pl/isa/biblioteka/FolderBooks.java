package pl.isa.biblioteka;

import com.fasterxml.jackson.databind.ObjectMapper;

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
            byte[] jsonData = Files.readAllBytes(Paths.get("books.json"));
            ObjectMapper folderBooks = new ObjectMapper();
            return Arrays.asList(folderBooks.readValue(jsonData, Book[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        List<Book> books = readBooks();
        books.forEach(System.out::println);
    }
}
