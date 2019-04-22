package br.com.bressy.isaque.contactList.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bressy.isaque.contactList.dtos.ContactDto;
import br.com.bressy.isaque.contactList.dtos.PersonDto;
import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.response.Response;
import br.com.bressy.isaque.contactList.services.PersonService;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {

	private Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@Value("${page.size}")
	private int pageSize;

	@GetMapping
	ResponseEntity<Response<Page<PersonDto>>> getAllPeople(@RequestParam(value = "page", defaultValue = "0") int page) {

		log.info("Buscando todas as pessoas");

		Response<Page<PersonDto>> response = new Response<>();

		PageRequest pageRequest = PageRequest.of(page, this.pageSize);
		Page<Person> people = this.personService.getPeople(pageRequest);
		Page<PersonDto> peopleDto = people.map(person -> this.convertToDto(person));

		response.setData(peopleDto);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<Response<PersonDto>> getPerson(@PathVariable Long id) {
		
		log.info("Buscando pessoa por id {}", id);

		Response<PersonDto> response = new Response<>();
		Optional<Person> person = this.personService.getPersonById(id);
		
		if (!person.isPresent()) {
			log.error("Pessoa com id {} não encontrado", id);
			response.getErrors().add("Pessoa com o id " + id + " não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.convertToDto(person.get()));
		
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<PersonDto>> create(@Valid @RequestBody PersonDto dto, BindingResult result) {
		Response<PersonDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<PersonDto>> update(@PathVariable Long id, @Valid @RequestBody PersonDto dto,
			BindingResult result) {
		Response<PersonDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<PersonDto>> delete(@PathVariable Long id, @Valid @RequestBody PersonDto dto,
			BindingResult result) {
		Response<PersonDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	private PersonDto convertToDto(Person person) {
		PersonDto dto = new PersonDto();
		dto.setName(person.getName());

		return dto;
	}

}
