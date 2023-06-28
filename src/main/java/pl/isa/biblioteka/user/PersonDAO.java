package pl.isa.biblioteka.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Person savePerson(Person person) {
        if (person.getId() == null) {
            entityManager.persist(person);
            return person;
        } else {
            entityManager.merge(person);
            return person;
        }
    }

    public boolean isLoginTaken(String login){
        // TODO: 28.06.2023  dodać jak zrobi sie metode pobrania wszystkich userow 
        return false;
    }

}