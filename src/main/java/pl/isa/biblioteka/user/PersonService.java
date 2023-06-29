package pl.isa.biblioteka.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.isa.biblioteka.book.Book;
import pl.isa.biblioteka.file.UserMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

@Service(value = "userDetailsService")
public class PersonService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());
//    public static List<Person> users = new ArrayList<>(PersonService.readUsers());
    public static List<Book> personBooks = new ArrayList<>();
    private final PersonDAO personDAO;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PersonRepository personRepository;
    public static List<Person> users = new ArrayList<>(PersonService.readUsers());

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static Person currentLogUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
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

    public List<Book> getPersonBooks() {
        return personBooks;
    }


    public void setPersonBooks(List<Book> personBooks) {
        PersonService.personBooks = personBooks;
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByLogin(login);
        return new CustomPersonDetails(person.map(userMapper::toDto).orElse(null));
    }

}