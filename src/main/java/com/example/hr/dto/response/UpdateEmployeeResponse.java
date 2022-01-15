package com.example.hr.dto.response;

import javax.validation.constraints.NotBlank;

public class UpdateEmployeeResponse {
	@NotBlank
	private String fullname;
	private String photo;
	public UpdateEmployeeResponse() {
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
