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

import br.com.bressy.isaque.contactList.dtos.PersonDto;
import br.com.bressy.isaque.contactList.response.Response;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {

	@GetMapping
	ResponseEntity<Response<Page<PersonDto>>> getAllPeople() {
		Response<Page<PersonDto>> response = new Response<>();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<Response<PersonDto>> getPerson(@PathVariable Long id) {
		Response<PersonDto> response = new Response<>();
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
}
