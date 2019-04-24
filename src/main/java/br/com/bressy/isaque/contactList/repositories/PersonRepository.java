package br.com.bressy.isaque.contactList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bressy.isaque.contactList.entities.Person;

@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByName(String name);
	
}
