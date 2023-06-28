package pl.isa.biblioteka.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.isa.biblioteka.user.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String login);

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);
}
