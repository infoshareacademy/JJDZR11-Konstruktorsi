package pl.isa.biblioteka;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonService {
    public static List<Person> readUsers() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("users.json"));
            ObjectMapper folderPerson = new ObjectMapper();
            return Arrays.asList(folderPerson.readValue(jsonData, Person[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
