package br.com.bressy.isaque.contactList.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContactRepositoryTest {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PersonRepository personRepository;

	private static final String CONTACT_DETAIL = "Contact Detail";
	private static final TypeEnum CONTACT_TYPE = TypeEnum.WHATSAPP;
	private static final String PERSON_NAME = "Teste";

	private Contact contact = new Contact();

	@Before
	public void setUp() throws Exception {
		Person person = new Person();
		person.setName(PERSON_NAME);

		contact.setDetail(CONTACT_DETAIL);
		contact.setType(CONTACT_TYPE);

		contact.setPerson(this.personRepository.save(person));

		contact = this.contactRepository.save(contact);
	}

	@After
	public final void tearDown() {
		this.contactRepository.deleteAll();
		this.personRepository.deleteAll();
	}

	@Test
	public void testFindByPersonId() {
		Long id = contact.getPerson().getId();
		PageRequest page = PageRequest.of(0, 10);
		Page<Contact> contacts = this.contactRepository.findByPersonId(id, page);

		assertEquals(1, contacts.getTotalElements());
	}

	@Test
	public void testFindByTypeAndDetail() {
		Optional<Contact> contactOpt = Optional
				.ofNullable(this.contactRepository.findByTypeAndDetail(CONTACT_TYPE, CONTACT_DETAIL));

		assertTrue(contactOpt.isPresent());
	}

}
