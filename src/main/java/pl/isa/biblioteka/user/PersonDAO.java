package pl.isa.biblioteka.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(Integer id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> findAll() {
        return entityManager.createQuery("FROM Person", Person.class).getResultList();
    }


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


    @Transactional
    public Person editUserId(Person person) {
            entityManager.merge(person);
            return person;
    }

    @Transactional
    public void delate(Integer id) {
        entityManager.remove(findById(id));
    }

    public boolean isLoginTaken(String login) {
        return findAll().stream().anyMatch(person -> person.getLogin().equalsIgnoreCase(login));
    }

}