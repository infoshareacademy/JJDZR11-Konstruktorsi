package pl.isa.biblioteka.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;
import pl.isa.biblioteka.book.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

@Service
public class PersonService {
    private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    public static List<Person> users = new ArrayList<>(PersonService.readUsers());

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    public static List<Book> personBooks = new ArrayList<>();
    public final List<Person> personList;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper, List<Person> personList) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
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

    public PersonDTO findById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can't find client by id"));
        return personMapper.toDto(person);
    }


    public String registerUserId(Person person) {
        boolean userExist = users.stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(person.getLogin()));
        if (userExist) {
            return "Login jest już zajęty, wybierz inny login";
        }
//        int nextId = users.size() + 1;
//        person.setId(nextId);
        personRepository.save(person);
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
        Person person = personRepository.findAll().stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Nie ma takiego użytkownika"));
        return new PersonDTO(person.getId(), person.getLogin(), person.getPassword(), person.getFirstName(), person.getSecondName(), person.getEmail());
    }


    public void updateUser(PersonDTO personDTO) {
            Optional<Person> person = personRepository.findById(personDTO.getId());

        if(!person.isPresent()){
            throw new IllegalArgumentException("Can't find person by id");
        }

        Person toUpdate = person.get();
        toUpdate.setLogin(personDTO.getLogin());
        toUpdate.setPassword(personDTO.getPassword());
        toUpdate.setFirstName(personDTO.getFirstName());
        toUpdate.setSecondName(personDTO.getSecondName());
        toUpdate.setEmail(personDTO.getEmail());

        personRepository.save(toUpdate);
    }
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

}