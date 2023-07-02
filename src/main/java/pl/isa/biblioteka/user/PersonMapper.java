package pl.isa.biblioteka.user;

import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public Person fromDto(PersonDTO personDTO) {
        return Person.builder()
                .id(personDTO.getId())
                .login(personDTO.getLogin())
                .password(personDTO.getPassword())
                .firstName(personDTO.getFirstName())
                .secondName(personDTO.getSecondName())
                .email(personDTO.getEmail())
                .build();
    }

    public PersonDTO toDto(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .login(person.getLogin())
                .password(person.getPassword())
                .firstName(person.getFirstName())
                .secondName(person.getSecondName())
                .email(person.getEmail())
                .build();
    }

}

