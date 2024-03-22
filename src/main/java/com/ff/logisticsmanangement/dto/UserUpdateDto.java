package com.ff.logisticsmanangement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

	@Size(min = 3, message = "userName should be atleast 3 characters")
	private String userName;

	@Size(min = 4, message = "password should be atleast 4 characters")
	private String userPassword;

	@Min(value = 1000000000L, message = "Phone number must be at least 10 digits long")
	private Long userPhoneNumber;

}
