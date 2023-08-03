package com.springboot.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "User Dto Model Information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private int id;
	@Schema(description = "User First Name")
	@NotEmpty(message = "First name should not be null or empty.")
	private String firstName;
	@Schema(description = "User Last Name")
	@NotEmpty(message = "Last name should not be null or empty.")
	private String lastName;
	@Schema(description = "User Email Address")
	@NotEmpty(message = "Email should not be null or empty.")
	@Email(message = "Email address should be valid.")
	private String email;
	
	

}
