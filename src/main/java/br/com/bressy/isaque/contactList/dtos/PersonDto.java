package br.com.bressy.isaque.contactList.dtos;

public class PersonDto {
	private String name;

	public PersonDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonDto [name=" + name + "]";
	}
}
