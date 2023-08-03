package com.springboot.restapi.mapper;

import com.springboot.restapi.dto.UserDTO;
import com.springboot.restapi.entity.User;

public class UserMapper {
	
	// convert USER JPA into User DTO
	public static UserDTO mapToUserDTO(User user) {
		UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
		
		return userDTO;
		
	}
	
	//Conver USER DTO into JPA
	
	public static User mapToUser(UserDTO userDTO) {
		
		User user = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail());
		
		return user;
		
	}

}
