package br.com.bressy.isaque.contactList.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bressy.isaque.contactList.entities.Contact;

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

}
