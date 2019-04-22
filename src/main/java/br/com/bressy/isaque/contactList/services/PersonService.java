package br.com.bressy.isaque.contactList.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Person;

@Service
public class PersonService {

	public Optional<Person> getPersonById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(new Person());
	}

}
