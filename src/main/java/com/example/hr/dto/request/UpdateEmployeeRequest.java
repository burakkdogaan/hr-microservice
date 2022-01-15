package com.example.hr.dto.request;

import javax.validation.constraints.NotBlank;

public class UpdateEmployeeRequest {
	
	@NotBlank
	private String fullname;
	private String photo;
	public UpdateEmployeeRequest() {
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
