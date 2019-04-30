package br.com.bressy.isaque.contactList.dtos;

import java.util.Optional;

public class ContactDto {
	private Optional<Long> id;
	private String detail;
	private String type;

	public ContactDto() {
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContactDto [detail=" + detail + ", type=" + type + "]";
	}
}
