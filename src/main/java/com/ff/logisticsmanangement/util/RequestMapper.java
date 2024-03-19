package com.ff.logisticsmanangement.util;

import org.springframework.stereotype.Component;

import com.ff.logisticsmanangement.dto.UserDto;
import com.ff.logisticsmanangement.entity.User;

import lombok.Builder;
@Component
@Builder
public class RequestMapper {
	public User requestUser(UserDto user) {
		return User.builder().userName(user.getUserName()).userPassword(user.getUserPassword())
				.userPhoneNumber(user.getUserPhoneNumber()).address(user.getAddress()).userRole(user.getUserRole())
				.build();
	}

}
