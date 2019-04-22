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
import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.response.Response;
import br.com.bressy.isaque.contactList.services.ContactService;
import br.com.bressy.isaque.contactList.services.PersonService;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactController {

	private Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;

	@Autowired
	private PersonService personService;

	@Value("${page.size}")
	private int pageSize;

	@GetMapping("/person/{id}")
	ResponseEntity<Response<Page<ContactDto>>> getAllContacts(@PathVariable Long id,
			@RequestParam(value = "page", defaultValue = "0") int page) {

		log.info("Buscando contatos por id da pessoa {} ", id);

		Response<Page<ContactDto>> response = new Response<>();
		Optional<Person> person = this.personService.getPersonById(id);
		
		if (!person.isPresent()) {
			log.error("Pessoa com o id {} não encontrada", id);
			response.getErrors().add("Pessoa com o id " + id + " não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
				
		PageRequest pageRequest = PageRequest.of(page, this.pageSize);
		Page<Contact> contacts = this.contactService.getContactsByPersonId(id, pageRequest);
		Page<ContactDto> contactsDto = contacts.map(contact -> this.convertToDto(contact));

		response.setData(contactsDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<Response<ContactDto>> getContact(@PathVariable Long id) {

		log.info("Buscando contato por id {}", id);

		Response<ContactDto> response = new Response<>();
		Optional<Contact> contact = this.contactService.getContactById(id);
		
		if (!contact.isPresent()) {
			log.error("Contato com id {} não encontrado", id);
			response.getErrors().add("Contato com o id " + id + " não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.convertToDto(contact.get()));
		
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<ContactDto>> create(@Valid @RequestBody ContactDto dto, BindingResult result) {
		Response<ContactDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<ContactDto>> update(@PathVariable Long id, @Valid @RequestBody ContactDto dto,
			BindingResult result) {
		Response<ContactDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<ContactDto>> delete(@PathVariable Long id, @Valid @RequestBody ContactDto dto,
			BindingResult result) {
		Response<ContactDto> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	private ContactDto convertToDto(Contact contact) {
		ContactDto dto = new ContactDto();
		dto.setDetail(contact.getDetail());
		dto.setType(contact.getType());

		return dto;
	}
}
