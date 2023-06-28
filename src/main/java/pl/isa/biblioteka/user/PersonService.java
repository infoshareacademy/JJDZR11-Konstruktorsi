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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    public static List<Person> users = new ArrayList<>(PersonService.readUsers());

    public static List<Book> personBooks = new ArrayList<>();

    public void setPersonBooks(List<Book> personBooks) {
        this.personBooks = personBooks;
    }

    public List<Book> getPersonBooks() {
        return personBooks;
    }

    public static Person currentLogUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                for (Person user : users) {
                    if (user.getLogin().equalsIgnoreCase(username)) {
                        personBooks = user.getPersonBooks();
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public String registerUserId(Person person) {
        boolean userExist = personDAO.isLoginTaken(person.getLogin());
        if (userExist) {
            return "Login jest już zajęty, wybierz inny login";
        }

        Person savedPerson = personDAO.savePerson(person);
        if (savedPerson != null) {
            return "Dodano użytkownika: " + savedPerson.getLogin() + ", możesz się zalogować";
        } else {
            return "Wystąpił problem podczas rejestracji użytkownika";
        }
    }

    public String editUserId(Person person, Integer id) {
        boolean userExist = personDAO.isLoginTaken(person.getLogin());
        if (userExist) {
            return "Użytkownik edytowany";
        }
        person.setId(id);
        personDAO.savePerson(person);
        return "Nie ma takiego użytkownika";
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