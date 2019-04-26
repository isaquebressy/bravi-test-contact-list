package br.com.bressy.isaque.contactList.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bressy.isaque.contactList.dtos.ContactDto;
import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.enums.TypeEnum;
import br.com.bressy.isaque.contactList.services.ContactService;
import br.com.bressy.isaque.contactList.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContactControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactService contactService;

	@MockBean
	private PersonService personService;

	private static final String URL_BASE = "/contact/";
	private static final String PERSON_NAME = "Person Name";
	private static final Long PERSON_ID = 1L;
	private static final Long CONTACT_ID = 1L;
	private static final String CONTACT_DETAIL = "7199999999";
	private static final TypeEnum CONTACT_TYPE = TypeEnum.WHATSAPP;

	@Test
	public void testCreateContact() throws JsonProcessingException, Exception {

		Contact contact = getContactData();

		BDDMockito.given(this.personService.getPersonById(Mockito.anyLong())).willReturn(Optional.of(new Person()));
		BDDMockito.given(this.contactService.persist(Mockito.any(Contact.class))).willReturn(contact);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "person/" + PERSON_ID).content(getJsonRequestPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(CONTACT_ID))
				.andExpect(jsonPath("$.data.detail").value(CONTACT_DETAIL))
				.andExpect(jsonPath("$.data.type").value(CONTACT_TYPE.toString()))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testGetContactById() throws Exception {

		Contact contact = getContactData();

		BDDMockito.given(this.contactService.getContactById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(contact));

		mvc.perform(MockMvcRequestBuilders.get(URL_BASE + CONTACT_ID).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(CONTACT_ID))
				.andExpect(jsonPath("$.data.detail").value(CONTACT_DETAIL))
				.andExpect(jsonPath("$.data.type").value(CONTACT_TYPE.toString()))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testUpdate() throws Exception {

		Contact contact = getContactData();

		BDDMockito.given(this.contactService.getContactById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(contact));

		mvc.perform(MockMvcRequestBuilders.put(URL_BASE + CONTACT_ID).content(getJsonRequestPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(CONTACT_ID))
				.andExpect(jsonPath("$.data.detail").value(CONTACT_DETAIL))
				.andExpect(jsonPath("$.data.type").value(CONTACT_TYPE.toString()))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	private String getJsonRequestPost() throws JsonProcessingException {
		ContactDto dto = new ContactDto();
		dto.setId(null);
		dto.setDetail(CONTACT_DETAIL);
		dto.setType(CONTACT_TYPE.toString());

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

	private Contact getContactData() {
		Person person = new Person();
		person.setId(PERSON_ID);
		person.setName(PERSON_NAME);

		Contact contact = new Contact();
		contact.setId(CONTACT_ID);
		contact.setDetail(CONTACT_DETAIL);
		contact.setType(CONTACT_TYPE);
		contact.setPerson(person);

		return contact;
	}
}
