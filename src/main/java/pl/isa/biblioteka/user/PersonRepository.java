package pl.isa.biblioteka.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
