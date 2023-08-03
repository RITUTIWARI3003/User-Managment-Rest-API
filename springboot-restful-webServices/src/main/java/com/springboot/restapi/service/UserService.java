package com.springboot.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.restapi.dto.UserDTO;
import com.springboot.restapi.entity.User;


public interface UserService {
	
	// for JPA Entities
	//User createUser(User user);
	
	//for DTO
	UserDTO createUser(UserDTO user);
	
	
	//for JPA
	//User getUserById(Integer userId);
	
	//for DTO
	UserDTO getUserById(Integer userId);
	
	//For JPA
	//List<User> getAllUsers();
	
	//For DTO
    List<UserDTO> getAllUsers();
	
    //for JPA
	//User updateUsers(User user);
    
    //For DTO
    UserDTO updateUsers(UserDTO user);
	
	void deleteUser(Integer userId);

}
