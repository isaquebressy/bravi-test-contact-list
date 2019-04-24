package br.com.bressy.isaque.contactList.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Person;
import br.com.bressy.isaque.contactList.repositories.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger log = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> getPersonById(Long id) {
		log.info("Buscando pessoa por id {}", id);
		return this.personRepository.findById(id);
	}

	public Page<Person> getPeople(PageRequest pageRequest) {
		log.info("Buscando todas as pessoas por página");
		return this.personRepository.findAll(pageRequest);
	}

	public Optional<Person> findByName(String name) {
		log.info("Buscando pessoa por nome {}", name);
		return Optional.ofNullable(this.personRepository.findByName(name));
	}

	public Person persist(Person person) {
		log.info("Persistindo pessoa {}", person);
		return this.personRepository.save(person);
	}

	public void remove(Person person) {
		log.info("Removendo pessoa {}", person);
		this.personRepository.delete(person);
	}

}
