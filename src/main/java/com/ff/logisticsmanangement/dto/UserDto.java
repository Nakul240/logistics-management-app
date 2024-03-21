package com.ff.logisticsmanangement.dto;

import com.ff.logisticsmanangement.entity.Address;
import com.ff.logisticsmanangement.util.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	@NotBlank(message = "userName should not be blank")
	@Size(min = 3, message = "userName should be atleast 3 characters")
	private String userName;
	
	@NotNull
	@Size(min = 4, message = "password should be atleast 4 characters")
	private String userPassword;
	
	@NotNull
	@Min(value = 1000000000L, message = "Phone number must be at least 10 digits long")
	private Long userPhoneNumber;
	
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private Role userRole;

}
