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

import br.com.bressy.isaque.contactList.dtos.PersonDto;
import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService personService;

	private static final String URL_BASE = "/person/";
	private static final String PERSON_NAME = "Person Name";
	private static final Long PERSON_ID = 1L;

	@Test
	public void testCreatePerson() throws Exception {

		Person person = getPersonData();

		BDDMockito.given(this.personService.findByName(Mockito.anyString())).willReturn(Optional.empty());
		BDDMockito.given(this.personService.persist(Mockito.any(Person.class))).willReturn(person);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE).content(getJsonRequestPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(PERSON_ID)).andExpect(jsonPath("$.data.name").value(PERSON_NAME))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testCreateInvalidPersonSameName() throws Exception {

		Person person = getPersonData();

		BDDMockito.given(this.personService.findByName(Mockito.anyString())).willReturn(Optional.of(new Person()));
		BDDMockito.given(this.personService.persist(Mockito.any(Person.class))).willReturn(person);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE).content(getJsonRequestPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Pessoa com nome já existente"))
				.andExpect(jsonPath("$.data").isEmpty());
	}

	@Test
	public void testGetPersonById() throws Exception {

		Person person = getPersonData();

		BDDMockito.given(this.personService.getPersonById(Mockito.anyLong())).willReturn(Optional.ofNullable(person));

		mvc.perform(MockMvcRequestBuilders.get(URL_BASE + PERSON_ID).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(PERSON_ID)).andExpect(jsonPath("$.data.name").value(PERSON_NAME))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testUpdate() throws Exception {

		Person person = getPersonData();

		BDDMockito.given(this.personService.getPersonById(Mockito.anyLong())).willReturn(Optional.ofNullable(person));

		mvc.perform(MockMvcRequestBuilders.put(URL_BASE + PERSON_ID).content(getJsonRequestPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(PERSON_ID)).andExpect(jsonPath("$.data.name").value(PERSON_NAME))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	private String getJsonRequestPost() throws JsonProcessingException {
		PersonDto dto = new PersonDto();
		dto.setId(null);
		dto.setName(PERSON_NAME);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

	private Person getPersonData() {
		Person person = new Person();
		person.setId(PERSON_ID);
		person.setName(PERSON_NAME);

		return person;
	}

}
