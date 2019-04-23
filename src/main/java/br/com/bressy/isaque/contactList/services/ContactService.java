package br.com.bressy.isaque.contactList.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.enums.TypeEnum;

@Service
public class ContactService {

	public Page<Contact> getContactsByPersonId(Long id, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return new PageImpl<Contact>(new ArrayList<Contact>());
	}

	public Optional<Contact> getContactById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(new Contact());
	}

	public void persist(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void remove(Contact contact) {
		// TODO Auto-generated method stub

	}

	public Optional<Contact> findByTypeAndDetail(TypeEnum type, String detail) {
		// TODO Auto-generated method stub
		return Optional.of(new Contact());
	}

}
