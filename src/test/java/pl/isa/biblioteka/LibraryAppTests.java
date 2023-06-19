package pl.isa.biblioteka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.isa.biblioteka.user.Person;
import pl.isa.biblioteka.user.PersonDAO;

@SpringBootTest
class LibraryAppTests {

	@Autowired
	PersonDAO personDAO;

	@Test
	void savePerson() {
		Person person = new Person(
				"KamilLogin",
				"KamilPass",
				"Kamil",
				"Koz",
				"k@k.pl");
		personDAO.savePerson(person);
	}

	@Test
	void contextLoads() {
	}

}
