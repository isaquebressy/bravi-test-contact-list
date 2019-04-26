package br.com.bressy.isaque.contactList.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bressy.isaque.contactList.entities.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	private static final String PERSON_NAME = "Teste";
	private static final String INVALID_NAME = "Invalido";
	private static Person person = new Person();

	@Before
	public void setUp() throws Exception {
		person.setName(PERSON_NAME);
		person = this.personRepository.save(person);
	}

	@After
	public final void tearDown() {
		this.personRepository.deleteAll();
	}

	@Test
	public void testFindByName() {
		Person person = this.personRepository.findByName(PERSON_NAME);
		assertEquals(PERSON_NAME, person.getName());
	}

	@Test
	public void testFindByInvalidName() {
		Person person = this.personRepository.findByName(INVALID_NAME);
		assertNull(person);
	}
}
