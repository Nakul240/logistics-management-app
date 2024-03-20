package com.ff.logisticsmanangement.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dao.OrderRepository;
import com.ff.logisticsmanangement.dao.UserRepository;
import com.ff.logisticsmanangement.dto.EstimatesDto;
import com.ff.logisticsmanangement.dto.LoadAndUnLoadDto;
import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Address;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Loading;
import com.ff.logisticsmanangement.entity.Order;
import com.ff.logisticsmanangement.entity.Unloading;
import com.ff.logisticsmanangement.entity.User;
import com.ff.logisticsmanangement.exception.DateMismatchException;
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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DistanceAndDurationEstimationService estimationService;

	// save the order
	public ResponseEntity<ResponseStructure<Order>> saveOrder(int carrierId, OrderDto orderDto) throws ParseException {

		Optional<Carrier> getCarrier = carrierRepository.findById(carrierId);
		if (getCarrier.isPresent()) {
			Carrier carrier = getCarrier.get();
			orderDto.setCarrier(carrier);
			Order order = requestMapper.getOrder(orderDto);
			order.setOrderStatus(OrderStatus.Pending);

			Loading loading = order.getLoading();
			Unloading unloading = order.getUnloading();

			EstimatesDto estimates = calculateFrieghtAndDaysEstimates(order.getCargo().getCargoWeight(),
					order.getLoading().getAddress(), order.getUnloading().getAddress());

			double frieghtCost = estimates.getFrieghtCost();
			int days = estimates.getDays();
			System.err.println(days);

			LocalDate loadingDate = loading.getLoadingDate();
			if (loadingDate.isAfter(LocalDate.now())) {

				unloading.setUnloadingDate(loadingDate.plusDays(days));
				loading.setLoadingTime(LocalTime.of(12, 00, 00));
				unloading.setUnloadingTime(LocalTime.of(12, 00, 00));
				order.setFreightCost(Math.round(frieghtCost * 100.0) / 100.0);
				Order savedOrder = orderRepository.save(order);

				ResponseStructure<Order> response = new ResponseStructure<Order>();
				response.setData(savedOrder);
				response.setStatusCode(HttpStatus.CREATED.value());
				response.setMessage("Success");
				return new ResponseEntity<ResponseStructure<Order>>(response, HttpStatus.CREATED);
			} else
				throw new DateMismatchException("Incorrect Date. Update Future Date");
		} else
			throw new IdNotFoundException("carrierId not found..!");
	}

	public EstimatesDto calculateFrieghtAndDaysEstimates(double cargoWeight, Address loadingAddress,
			Address unloadingAddress) throws ParseException {

		String loadAddress = loadingAddress.getStreetName() + " " + loadingAddress.getDistrict();
		String unloadAddress = unloadingAddress.getStreetName() + " " + unloadingAddress.getDistrict();

		EstimatesDto estimates = estimationService.getTheDistanceAndTime(loadAddress, unloadAddress);

		int kmrate = 50;
		int kgrate = 18;

		double distance = estimates.getDistance() / 1000;

		estimates.setFrieghtCost((kmrate * distance) + (kgrate * cargoWeight));

		long timeInMiliSeconds = estimates.getTime();

		int days = (int) (timeInMiliSeconds / (24 * 60 * 60 * 1000)) + 2;
		estimates.setDays(days);

		return estimates;
	}

	public ResponseEntity<?> loadOrder(int orderId, LoadAndUnLoadDto loadingUsers) {

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new IdNotFoundException("Not Valid OrderId!"));

		List<User> users = userRepository.findAllById(loadingUsers.getUserIds());

		order.setLoadingUser(users);
		order.getLoading().setLoadingTime(LocalTime.now());
		order.setOrderStatus(OrderStatus.InTransit);

		orderRepository.save(order);

		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setData("Cargo Loaded Successfully!");
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

	public ResponseEntity<?> unloadOrder(int orderId, LoadAndUnLoadDto loadingUsers) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new IdNotFoundException("Not Valid OrderId!"));

		List<User> users = userRepository.findAllById(loadingUsers.getUserIds());

		order.setUnloadingUser(users);
		order.getUnloading().setUnloadingTime(LocalTime.now());
		order.setOrderStatus(OrderStatus.Delivered);

		orderRepository.save(order);

		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setData("Cargo Delivered Successfully!");
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

}
