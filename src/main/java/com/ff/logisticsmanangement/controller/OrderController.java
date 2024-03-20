package com.ff.logisticsmanangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Order;
import com.ff.logisticsmanangement.service.OrderService;

@RestController
@RequestMapping("/logistics/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	//save the order
	@PostMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@PathVariable int carrierId,
			@RequestBody OrderDto order) {
		return orderService.saveOrder(carrierId, order);
	}

}
