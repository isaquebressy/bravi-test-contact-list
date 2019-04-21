package br.com.bressy.isaque.contactList.dtos;

public class ContactDto {
	private String detail;
	private int type;

	public ContactDto() {
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ContactDto [detail=" + detail + ", type=" + type + "]";
	}
}
