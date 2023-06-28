package pl.isa.biblioteka.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.isa.biblioteka.user.CustomPersonDetails;
import pl.isa.biblioteka.user.Person;
import pl.isa.biblioteka.user.PersonDTO;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public PersonDTO toDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setLogin(person.getLogin());
        personDTO.setPassword(person.getPassword());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setSecondName(person.getSecondName());
        personDTO.setEmail(person.getEmail());
        personDTO.setRole(person.getRole());
        return personDTO;
    }

    public Person toEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setLogin(personDTO.getLogin());
        person.setEmail(personDTO.getEmail());
        person.setFirstName(personDTO.getFirstName());
        person.setSecondName(personDTO.getSecondName());
        person.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        person.setRole(personDTO.getRole());
        return person;
    }

    public PersonDTO toDtoFromPrincipal(CustomPersonDetails principal) {
        return principal.getPersonDTO();
    }
}
