package br.com.bressy.isaque.contactList.dtos;

import java.util.Optional;

public class PersonDto {
	private Optional<Long> id;
	private String name;

	public PersonDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PersonDto [name=" + name + "]";
	}
}
