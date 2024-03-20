package com.ff.logisticsmanangement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dao.OrderRepository;
import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Order;
import com.ff.logisticsmanangement.exception.IdNotFoundException;
import com.ff.logisticsmanangement.util.OrderStatus;
import com.ff.logisticsmanangement.util.RequestMapper;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RequestMapper requestMapper;
	@Autowired
	private CarrierRepository carrierRepository;

	// save the order
	public ResponseEntity<ResponseStructure<Order>> saveOrder(int carrierId, OrderDto order) {

		Optional<Carrier> getCarrier = carrierRepository.findById(carrierId);
		if (getCarrier.isPresent()) {
			Carrier carrier = getCarrier.get();
			order.setCarrier(carrier);
			Order getOrder = requestMapper.getOrder(order);
			getOrder.setOrderStatus(OrderStatus.Pending);
			Order savedOrder = orderRepository.save(getOrder);

			ResponseStructure<Order> response = new ResponseStructure<Order>();
			response.setData(savedOrder);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("sucess");
			return new ResponseEntity<ResponseStructure<Order>>(response, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("carrierId not found..!");
		}
	}

}
