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

import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.enums.TypeEnum;
import br.com.bressy.isaque.contactList.repositories.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContactServiceTest {

	@MockBean
	private ContactRepository contactRepository;

	@Autowired
	private ContactService contactService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.contactRepository.findByPersonId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Contact>(new ArrayList<Contact>()));
		BDDMockito.given(this.contactRepository.findByTypeAndDetail(Mockito.any(TypeEnum.class), Mockito.anyString()))
				.willReturn(new Contact());
		BDDMockito.given(this.contactRepository.findById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(new Contact()));
		BDDMockito.given(this.contactRepository.save(Mockito.any(Contact.class))).willReturn(new Contact());
	}

	@Test
	public void testFindByPersonId() {
		Page<Contact> contacts = this.contactService.getContactsByPersonId(1L, PageRequest.of(0, 10));

		assertNotNull(contacts);
	}

	@Test
	public void testGetContactById() {
		Optional<Contact> contactOpt = this.contactService.getContactById(1L);

		assertTrue(contactOpt.isPresent());
	}

	@Test
	public void testPersist() {
		Contact contact = this.contactService.persist(new Contact());

		assertNotNull(contact);
	}

	@Test
	public void testFindByTypeAndDetail() {
		Optional<Contact> contactOpt = this.contactService.findByTypeAndDetail(TypeEnum.WHATSAPP, "71999999999");

		assertTrue(contactOpt.isPresent());
	}

}
