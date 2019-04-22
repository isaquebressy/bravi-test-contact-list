package br.com.bressy.isaque.contactList.dtos;

import br.com.bressy.isaque.contactList.enums.TypeEnum;

public class ContactDto {
	private String detail;
	private TypeEnum type;

	public ContactDto() {
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ContactDto [detail=" + detail + ", type=" + type + "]";
	}
}
