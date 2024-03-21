package com.ff.logisticsmanangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.dto.UserDto;
import com.ff.logisticsmanangement.entity.User;
import com.ff.logisticsmanangement.service.UserService;

@RestController
@RequestMapping("/logistics/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/saveUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody UserDto user) {
		System.out.println(user);
		return userService.saveUser(user);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}

	@GetMapping("/getAlUser")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}

}
