package com.ff.logisticsmanangement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ff.logisticsmanangement.dao.UserRepository;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.dto.UserDto;
import com.ff.logisticsmanangement.entity.User;
import com.ff.logisticsmanangement.exception.IdNotFoundException;
import com.ff.logisticsmanangement.util.RequestMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RequestMapper requestmapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<ResponseStructure<User>> saveUser(UserDto user, BindingResult result) {

		if (result.hasErrors()) {

			String message = "";

			for (FieldError error : result.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}

		User users = requestmapper.requestUser(user, passwordEncoder);
		User savedUser = userRepository.save(users);

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(savedUser);

		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			User recievedUser = user.get();
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Ok");
			responseStructure.setData(recievedUser);

			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("User not found");
		}

	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		List<User> usersList = userRepository.findAll();

		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Ok");
		responseStructure.setData(usersList);

		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user, BindingResult result) {
		
		if (result.hasErrors()) {

			String message = "";

			for (FieldError error : result.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}

		Optional<User> recievedUser = userRepository.findById(userId);
		if (recievedUser.isPresent()) {
			User users = recievedUser.get();
			if (user.getUserName() != null) {
				users.setUserName(user.getUserName());
			}
			if (user.getPassword() != null) {
				users.setPassword(user.getPassword());
			}
			if (user.getUserPhoneNumber() != null) {
				users.setUserPhoneNumber(user.getUserPhoneNumber());
			}

			User savedUser = userRepository.save(users);

			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Ok");
			responseStructure.setData(savedUser);

			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		}
		throw new IdNotFoundException("User not present");
	}

	public ResponseEntity deleteUser(int userId) {
		Optional<User> getUser = userRepository.findById(userId);
		if (getUser.isPresent()) {
			User user = getUser.get();
			userRepository.deleteById(userId);

			return new ResponseEntity(HttpStatus.OK);
		} else {
			throw new IdNotFoundException("User is not present");
		}
	}

}
