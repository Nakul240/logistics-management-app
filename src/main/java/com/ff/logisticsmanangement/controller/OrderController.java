package com.ff.logisticsmanangement.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

	// save the order
	@Operation(description = "Order details will be saved in the database", summary = "To Create Order info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@RequestBody OrderDto order)
			throws ParseException {
		return orderService.saveOrder(order.getCarrierId(), order);
	}

	// update the loading users
	@Operation(description = "Update the loadingUsers in the database", summary = "To Update loadingUsers info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/load/{orderId}")
	public ResponseEntity<?> loadOrder( @RequestBody LoadAndUnLoadDto loadingUsers, BindingResult result, @PathVariable int orderId) {
		return orderService.loadOrder(orderId, loadingUsers, result);
	}

	// update the unloading users
	@Operation(description = "Update the unloadingUsers in the database", summary = "To Update unloadingUsers info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/unload/{orderId}")
	public ResponseEntity<?> unloadOrder(@RequestBody LoadAndUnLoadDto loadingUsers, BindingResult result, @PathVariable int orderId) {
		return orderService.unloadOrder(orderId, loadingUsers, result);
	}
	
	@Operation(description = "Delete the order", summary = "deleting the order")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable int orderId){
		return orderService.deleteOrderById(orderId);
	}
	
	
	@Operation(description = "Get all the order details", summary = "all the order details")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders(){
		
		return orderService.getAllOrders();
		
	}
	
	@Operation(description = "update the order details using order id", summary = "update the order details")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/{orderId}")
	public ResponseEntity<?> updateOrder(@PathVariable int orderId, @RequestBody OrderDto orderDto){
		
		return orderService.updateOrder(orderId, orderDto);
	}
	

}
