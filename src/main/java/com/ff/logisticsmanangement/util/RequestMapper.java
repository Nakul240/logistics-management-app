package com.ff.logisticsmanangement.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.dto.UserDto;
import com.ff.logisticsmanangement.entity.Order;
import com.ff.logisticsmanangement.entity.User;

import lombok.Builder;

@Component
@Builder

public class RequestMapper {
	public User requestUser(UserDto user,PasswordEncoder passwordEncoder) {
		return User.builder().userName(user.getUserName()).password(user.getUserPassword())
				.userPhoneNumber(user.getUserPhoneNumber()).address(user.getAddress()).userRole(user.getUserRole())
				.build();
	}

	public Order getOrder(OrderDto order) {
		Order receivedOrder = Order.builder().additionalInfo(order.getAdditionalInfo()).cargo(order.getCargo())
					.loading(order.getLoading()).unloading(order.getUnloading()).build();
		return receivedOrder;
	}

}
