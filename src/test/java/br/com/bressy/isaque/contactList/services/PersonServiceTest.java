package br.com.bressy.isaque.contactList.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.personRepository.findById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(new Person()));
		BDDMockito.given(this.personRepository.findAll(Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Person>(new ArrayList<Person>()));
		BDDMockito.given(this.personRepository.findByName(Mockito.anyString())).willReturn(new Person());
		BDDMockito.given(this.personRepository.save(Mockito.any(Person.class))).willReturn(new Person());
	}

	@Test
	public void testGetPersonById() {
		Optional<Person> person = this.personService.getPersonById(1L);

		assertTrue(person.isPresent());
	}

	@Test
	public void testGetPeople() {
		Page<Person> people = this.personService.getPeople(PageRequest.of(0, 10));

		assertNotNull(people);
	}

	@Test
	public void testFindByName() {
		Optional<Person> personOpt = this.personService.findByName("Someone");

		assertTrue(personOpt.isPresent());
	}

	@Test
	public void testPersist() {
		Person person = this.personService.persist(new Person());

		assertNotNull(person);
	}

}
