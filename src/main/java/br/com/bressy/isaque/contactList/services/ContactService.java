package br.com.bressy.isaque.contactList.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.enums.TypeEnum;
import br.com.bressy.isaque.contactList.repositories.ContactRepository;

@Service
public class ContactService {

	private static final Logger log = LoggerFactory.getLogger(ContactService.class);

	@Autowired
	private ContactRepository contactRepository;

	public Page<Contact> getContactsByPersonId(Long id, PageRequest pageRequest) {
		log.info("Buscando contatos por personId {}", id);
		return this.contactRepository.findByPersonId(id, pageRequest);
	}

	public Optional<Contact> getContactById(Long id) {
		log.info("Buscando contatos por id {}", id);
		return this.contactRepository.findById(id);
	}

	public Contact persist(Contact contact) {
		log.info("Persistindo contato {}", contact);
		return this.contactRepository.save(contact);
	}

	public void remove(Contact contact) {
		log.info("Removendo contato {}", contact);
		this.contactRepository.delete(contact);
	}

	public Optional<Contact> findByTypeAndDetail(TypeEnum type, String detail) {
		log.info("Buscando contatos por tipo {} e detalhes {}", type, detail);
		return Optional.ofNullable(this.contactRepository.findByTypeAndDetail(type, detail));
	}

}
