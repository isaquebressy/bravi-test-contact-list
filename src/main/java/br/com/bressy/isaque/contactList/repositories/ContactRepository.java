package br.com.bressy.isaque.contactList.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.bressy.isaque.contactList.entities.Contact;
import br.com.bressy.isaque.contactList.enums.TypeEnum;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "ContactRepository.findByPersonId", query = "SELECT c FROM Contact c WHERE c.person.id = :personId") })
public interface ContactRepository extends JpaRepository<Contact, Long> {

	Page<Contact> findByPersonId(@Param("personId") Long personId, Pageable pageable);

	Contact findByTypeAndDetail(TypeEnum type, String detail);
	
}
