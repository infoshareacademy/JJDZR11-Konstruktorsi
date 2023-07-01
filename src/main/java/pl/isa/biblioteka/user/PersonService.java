package pl.isa.biblioteka.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.isa.biblioteka.book.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    public static List<Person> users = new ArrayList<>(PersonService.readUsers());

    private final PersonRepository personRepository;
    public static List<Book> personBooks = new ArrayList<>();
    public final List<Person> personList;

    public PersonService(PersonRepository personRepository, List<Person> personList) {
        this.personRepository = personRepository;
        this.personList = personList;
    }


    public void setPersonBooks(List<Book> personBooks) {
        this.personBooks = personBooks;
    }

    public List<Book> getPersonBooks() {
        return personBooks;
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person logPerson(String name) {
        return personRepository.findByLogin(name);
    }

    public Person currentLogUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                return logPerson(username);
            }
        }
        return null;
    }

    public static String registerUserId(Person person) {
        boolean userExist = users.stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(person.getLogin()));
        if (userExist) {
            return "Login jest już zajęty, wybierz inny login";
        }
        int nextId = users.size() + 1;
        person.setId(nextId);
        users.add(person);
        LOGGER.info("Dodano użytkownika: " + person.getLogin());
        return "Dodano użytkownika: " + person.getLogin() + ", możesz się zalogować";
    }

    public static String editUserId(Person person, Integer id) {
        boolean userExist = users.stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(person.getLogin()));
        if (userExist) {
            return "Login jest już zajęty, wybierz inny login";
        }
        person.setId(id);
        users.add(person);
        return "Dodano użytkownika, możesz się zalogować";
    }


    public static List<Person> readUsers() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("users.json"));
            ObjectMapper folderPerson = new ObjectMapper();
            LOGGER.info("------User read correctly------");
            return Arrays.asList(folderPerson.readValue(jsonData, Person[].class));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("------User not read error------");
            return Collections.emptyList();
        }
    }

    public static void saveUsers() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Person> personList = users;
        try {
            mapper.writeValue(new File("users.json"), personList);
            LOGGER.info("------User saved correctly------");
        } catch (IOException e) {
            LOGGER.info("------User not saved error------");
            e.printStackTrace();
        }
    }

    public PersonDTO findId(Integer id) {
        Person person = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Nie ma takiego użytkownika"));
        return new PersonDTO(person.getId(), person.getLogin(), person.getPassword(), person.getFirstName(), person.getSecondName(), person.getEmail());
    }

    public void delete(Integer id) {
        users.removeIf(s -> s.getId().equals(id));
        saveUsers();
    }
}