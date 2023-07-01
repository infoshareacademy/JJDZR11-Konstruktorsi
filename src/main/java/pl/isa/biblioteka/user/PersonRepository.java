package pl.isa.biblioteka.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.isa.biblioteka.book.Book;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByLogin(String login);
}
