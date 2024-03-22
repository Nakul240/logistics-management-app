package com.ff.logisticsmanangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/logistics/users")
public class UserController {

	@Autowired
	private UserService userService;

	/*	user info is saved, no authorization need.
	 * 
	 * 	@returns saved user info
	 */
	@Operation(description = "User will be saved in the database", summary = "To Create User info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody UserDto user, BindingResult result) {
		return userService.saveUser(user, result);
	}

	/*	get a particular user based on the id provided
	 * 
	 * 	@returns fetched user info
	 */
	@Operation(description = "Get user based on the given Id ", summary = "To get user")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}

	/*
	 * @returns all the users in the database
	 */
	@Operation(description = "Get all users from database", summary = "To get all users")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		return userService.getAllUsers();
	}

	/*
	 * update user info , parameters to be updated only to be provided
	 * 
	 *@returns updated user info
	 */
	@Operation(description = "User Details will be updated into the database", summary = "To update user info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@Valid @RequestBody User user, BindingResult result,  @PathVariable int userId ) {
		return userService.updateUser(userId, user, result);
	}

	/*
	 * deletes a particular user based on Id provided
	 */
	@Operation(description = "User will be deleted from the database", summary = "To Delete user Info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "404") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}

}
