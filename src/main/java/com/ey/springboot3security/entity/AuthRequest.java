package com.ey.springboot3security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message="Please enter email address")
	@Email(message="invalid email address") 
	private String email;
	
	@NotBlank(message="Please enter password")
	private String password;

	public String getUsername() {
		return email;
	}


}
