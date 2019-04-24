package br.com.bressy.isaque.contactList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bressy.isaque.contactList.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByName(String name);
	
}
