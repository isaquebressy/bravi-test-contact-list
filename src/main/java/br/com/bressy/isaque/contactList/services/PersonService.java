package br.com.bressy.isaque.contactList.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Person;

@Service
public class PersonService {

	public Optional<Person> getPersonById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(new Person());
	}

	public Page<Person> getPeople(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return new PageImpl<Person>(new ArrayList<Person>());
	}

	public Optional<Person> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.of(new Person());
	}

	public Person persist(Person person) {
		// TODO Auto-generated method stub
		return new Person();
	}

}
