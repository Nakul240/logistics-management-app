package com.ff.logisticsmanangement.dto;

import com.ff.logisticsmanangement.entity.Address;
import com.ff.logisticsmanangement.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private String userName;
	private String userPassword;
	private Long userPhoneNumber;
	private Address address;
	@Enumerated(EnumType.STRING)
	private Role userRole;

}
