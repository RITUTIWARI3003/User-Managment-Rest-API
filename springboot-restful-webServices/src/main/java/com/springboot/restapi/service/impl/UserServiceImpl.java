package com.springboot.restapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.Exception.EmailAlreadyExistsException;
import com.springboot.restapi.Exception.ResourceNotFoundException;
import com.springboot.restapi.dto.UserDTO;
import com.springboot.restapi.entity.User;
import com.springboot.restapi.mapper.UserMapper;
import com.springboot.restapi.repository.UserRepository;
import com.springboot.restapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	
	
	private ModelMapper modelMapper;
	
	@Autowired
   private UserRepository userRepository;
	
	// For JPA Entities
//	@Override
//	public User createUser(User user) {
//		System.out.println("Save happened");
//		return userRepository.save(user);
//	}
	
	//Fot DTO
	@Override
	public UserDTO createUser(UserDTO userDto) {
		// 1. convert user dto into user entities for storing jpa into database
		//User user = UserMapper.mapToUser(userDto);
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for a user");
		}
		User user  = modelMapper.map(userDto, User.class);
		// 2. save this object into database but return dto to the client
		User savedUser = userRepository.save(user);
		
		//convert JPA Entities to DTO
		//UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
		UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
		return savedUserDTO;
	}

//	For JPA
	
//	@Override
//	public User getUserById(Integer userId) {
//		Optional<User> user = userRepository.findById(userId);
//		return user.get();
//	}
	
	// For DTO
	
	@Override
	public UserDTO getUserById(Integer userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(
				() -> new ResourceNotFoundException("user","id",userId)); 
		//return UserMapper.mapToUserDTO(user);
		return modelMapper.map(user, UserDTO.class);
	}
	
	//For JPA
//	@Override
//	public List<User> getAllUsers() {
//		List<User> users = userRepository.findAll();
//		return users;
//	}
	
	//for DTO
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		//return  users.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());
		return  users.stream().map((user)->modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}
	
	//for JPA
//	@Override
//	public User updateUsers(User user) {
//		User existingUser = userRepository.findById(user.getId()).get();
//		existingUser.setFirstName(user.getFirstName());
//		existingUser.setLastName(user.getLastName());
//		existingUser.setEmail(user.getEmail());
//		User updatedUser = userRepository.save(existingUser);
//		return updatedUser;
//	}
	
	//For DTO
	@Override
	public UserDTO updateUsers(UserDTO user) {
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User","id",user.getId()));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
		//return UserMapper.mapToUserDTO(updatedUser);
		return modelMapper.map(updatedUser, UserDTO.class);
	}
	@Override
	public void deleteUser(Integer userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User","id",userId));
      userRepository.deleteById(userId);		
	}
		

}
