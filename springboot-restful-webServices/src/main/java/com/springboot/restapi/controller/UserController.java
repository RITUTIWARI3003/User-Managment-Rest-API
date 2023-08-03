package com.springboot.restapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.springboot.restapi.Exception.ErrorDetails;
import com.springboot.restapi.Exception.ResourceNotFoundException;
import com.springboot.restapi.dto.UserDTO;
import com.springboot.restapi.entity.User;
import com.springboot.restapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD Rest API for User Resource"
		)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Using JPA Entities
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		User savedUser = userService.createUser(user);
//		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
//		
//	}
	//Using User DTO
	@Operation(
			summary = "Create User REST API")
	@ApiResponse(
			responseCode = "201")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
		UserDTO savedUser = userService.createUser(user);
		return new ResponseEntity<UserDTO>(savedUser,HttpStatus.CREATED);
		
	}
	
	// By Using JPA 
//	@GetMapping("{id}")
//	public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId){
//		User getUser = userService.getUserById(userId);
//		return new ResponseEntity<User>(getUser,HttpStatus.OK);
//	}
	
	// By Using DTO
	@Operation(
			summary = "Get User by ID REST API")
	@ApiResponse(
			responseCode = "200")
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer userId){
		UserDTO getUser = userService.getUserById(userId);
		return new ResponseEntity<UserDTO>(getUser,HttpStatus.OK);
	}
 
	// Using DTO
//	@GetMapping
//	public ResponseEntity<List<User>> getAllUsers(){
//		List<User> users = userService.getAllUsers();
//		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
//		
//	}
	
	//Using JPA
	@Operation(
			summary = "Get All User REST API")
	@ApiResponse(
			responseCode = "200")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(users,HttpStatus.OK);
		
	}
	
	// FOR JPA
	
//	@PutMapping("{id}")
//	public ResponseEntity<User> updateUsers(@PathVariable Integer id , 
//			                                @RequestBody User user){
//		
//		user.setId(id);
//		User updateUser = userService.updateUsers(user);
//		
//		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
//		
//	}
	
	//For DTO
	@Operation(
			summary = "Update User REST API")
	@ApiResponse(
			responseCode = "200")
	@PutMapping("{id}")
	public ResponseEntity<UserDTO> updateUsers(@PathVariable Integer id , 
		@Valid	@RequestBody UserDTO user){
		
		user.setId(id);
		UserDTO updateUser = userService.updateUsers(user);
		
		return new ResponseEntity<UserDTO>(updateUser,HttpStatus.OK);
		
	}
	@Operation(
			summary = "Delete User REST API")
	@ApiResponse(
			responseCode = "200")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User Successfully Deleted !!" , HttpStatus.OK);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException 
//			resourceNotFoundException,WebRequest webRequest){
//		ErrorDetails errorDetails = new ErrorDetails(
//				 LocalDateTime.now(),
//				 resourceNotFoundException.getMessage(),
//				 webRequest.getDescription(false),
//				 "USER_NOT_FOUND"				 
//				);
//		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
//				
//	}

}
