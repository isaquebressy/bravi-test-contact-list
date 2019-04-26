package br.com.bressy.isaque.contactList.dtos;

public class ContactDto {
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContactDto [detail=" + detail + ", type=" + type + "]";
	}
}
