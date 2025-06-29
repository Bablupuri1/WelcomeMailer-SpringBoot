package com.ecom.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreType(value = false)
public class UserPayload {

	@Id
	private Long id;

	private String name;
	private String email;
	
	
	//private String password;//this is genrated by other service automatically not by me

	private String mobile;
	private String address;
	private String role;
	

}
