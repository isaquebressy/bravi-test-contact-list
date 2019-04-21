package br.com.bressy.isaque.contactList.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.bressy.isaque.contactList.dtos.ContactDto;
import br.com.bressy.isaque.contactList.response.Response;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactController {

	@GetMapping
	ResponseEntity<Response<Page<ContactDto>>> getAllContacts() {
		Response<Page<ContactDto>> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<Response<ContactDto>> getContact(@PathVariable Long id) {
		Response<ContactDto> response = new Response<>();
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
}
