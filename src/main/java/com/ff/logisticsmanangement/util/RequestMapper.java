package com.ff.logisticsmanangement.util;

import org.springframework.stereotype.Component;

import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.entity.Order;

import lombok.Builder;

@Builder
@Component
public class RequestMapper {

	public Order getOrder(OrderDto order) {
		Order receivedOrder = Order.builder().additionalInfo(order.getAdditionalInfo()).cargo(order.getCargo())
				.carrier(order.getCarrier()).loading(order.getLoading()).unloading(order.getUnloading()).build();
		return receivedOrder;
	}

}
