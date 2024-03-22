package com.ff.logisticsmanangement.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.LoadAndUnLoadDto;
import com.ff.logisticsmanangement.dto.OrderDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Order;
import com.ff.logisticsmanangement.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/logistics/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	/*
	 * save an order including carrier, cargo, loanding and unloading points
	 * 
	 * @returns the placed order info
	 */
	@Operation(description = "Order details will be saved in the database", summary = "To Create Order info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PostMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@PathVariable int carrierId, @RequestBody OrderDto order)

			throws ParseException {
		return orderService.saveOrder(order.getCarrierId(), order);
	}

	/*
	 * update a particular order's loading users list
	 * 
	 * @returns the Success message on updation
	 */
	@Operation(description = "Update the loadingUsers in the database", summary = "To Update loadingUsers info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/load/{orderId}")
	public ResponseEntity<?> loadOrder(@RequestBody LoadAndUnLoadDto loadingUsers, BindingResult result,
			@PathVariable int orderId) {
		return orderService.loadOrder(orderId, loadingUsers, result);
	}

	/*
	 * update a particular order's unloading users list
	 * 
	 * @returns the Success message on updation
	 */
	@Operation(description = "Update the unloadingUsers in the database", summary = "To Update unloadingUsers info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/unload/{orderId}")
	public ResponseEntity<?> unloadOrder(@RequestBody LoadAndUnLoadDto loadingUsers, BindingResult result,
			@PathVariable int orderId) {
		return orderService.unloadOrder(orderId, loadingUsers, result);
	}

	/*
	 * deletes a particular order based on the id , Admin only
	 */
	@Operation(description = "Delete the order", summary = "deleting the order")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable int orderId) {
		return orderService.deleteOrderById(orderId);
	}

	/*
	 * @returns all the orders from the database
	 */
	@Operation(description = "Get all the order details", summary = "all the order details")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {

		return orderService.getAllOrders();

	}

	/*
	 * updates a order , any parameter can be updated
	 * 
	 * @returns updated order info
	 */
	@Operation(description = "update the order details using order id", summary = "update the order details")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/{orderId}")
	public ResponseEntity<?> updateOrder(@PathVariable int orderId, @RequestBody OrderDto orderDto) {

		return orderService.updateOrder(orderId, orderDto);
	}

}
