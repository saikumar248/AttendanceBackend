package com.attendance.app.service;

import java.io.IOException;
import java.util.List;
 


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.attendance.app.dto.UserDto;
import com.attendance.app.exception.DuplicateException;
import com.attendance.app.exception.ResourceNotFound;
import com.attendance.app.model.User;
import com.attendance.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

//	public User registerUser(User user) {
//		if (userRepository.findByEmail(user.getEmail()) != null
//				|| userRepository.findByMobile(user.getMobile()) != null) {
//			throw new DuplicateException("User already exists!");
//		}
//		return userRepository.save(user);
//	}
//	
    public User getUserImage(long id) {
        return userRepository.findById(id).orElse(null);
    }
//
//	public User loginUser(String email, String password) {
//		User user = userRepository.findByEmail(email);
//		if (user == null || !user.getPassword().equals(password)) {
//			throw new ResourceNotFound("Invalid credentials!");
//		}
//		return user;
//	}
//
	public ResponseEntity<List<User>> fetchAll() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}

    public User addUser(User user, MultipartFile imageFile) throws IOException {
        user.setImageName(imageFile.getOriginalFilename());
        user.setImageType(imageFile.getContentType());
        user.setProfilePic(imageFile.getBytes());
//        System.out.println("Gone");
//        System.out.println("Received file: " + (imageFile != null ? imageFile.getOriginalFilename() : "No file received"));


        return userRepository.save(user);
    }
	
//	public ResponseEntity<List<UserDto>> fetch() {
//	List<User> data = userRepository.findAll();
//
//	User existedData = (User) data;
//	@SuppressWarnings("unchecked")
//	List<UserDto> userDto = (List<UserDto>) modelMapper.map(existedData, UserDto.class);
//	return ResponseEntity.status(HttpStatus.OK).body(userDto);
//}
//	
//
//	public void deleteUser(Long id) {
//		User user = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFound("User not found with ID: " + id));
//		userRepository.delete(user);
//	}
//
//	public User updateUser(Long id, User updatedUser) {
//		User existingUser = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFound("User not found with ID: " + id));
//		existingUser.setLastName(updatedUser.getLastName());
//		existingUser.setFirstName(updatedUser.getFirstName());
//		existingUser.setEmail(updatedUser.getEmail());
//		existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
//		existingUser.setPassword(updatedUser.getPassword());
//		return userRepository.save(existingUser);
//	}
//	
//	
	 public User fetchByMobile(String mobile) {
	        return userRepository.findByMobile(mobile);  // This should not be null if the injection works
	    }
//	
//	
//	public User updateUserByEmail(String email, User updatedUser) {
//	    User existingUser = userRepository.findByEmail(email);
//	    		
//	   	if (existingUser == null ) {
//	    			throw new ResourceNotFound("Invalid credentials!");
//	    }
//	           
//	    existingUser.setLastName(updatedUser.getLastName());
//	    existingUser.setFirstName(updatedUser.getFirstName());
//	    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
//	    existingUser.setPassword(updatedUser.getPassword());
//	    return userRepository.save(existingUser);
//	}
//
//	public User updateUserPasswordByEmail(String email, String newPassword) {
//	    User existingUser = userRepository.findByEmail(email);
//
//	    if (existingUser == null) {
//	        throw new ResourceNotFound("Invalid credentials!");
//	    }
//
//	    existingUser.setPassword(newPassword);
//	    return userRepository.save(existingUser);
//	}
//
//

	
	
	

}
