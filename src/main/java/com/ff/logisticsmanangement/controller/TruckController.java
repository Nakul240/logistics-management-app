package com.ff.logisticsmanangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.GetAllTrucksDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Truck;
import com.ff.logisticsmanangement.service.TruckService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/logistics/trucks")
public class TruckController {

	@Autowired
	private TruckService service;

	/*
	 * add a truck to the database , truck has a carrier id i.e the carrier company it belongs to..
	 * 
	 * @returns saved truck
	 */
	@Operation(description = "A truck of Specific Carrier company is Added to the DB", summary ="Add a truck")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<ResponseStructure<Truck>> addTruck(@Valid @RequestBody Truck truck, BindingResult result) {
		return service.addTruck(truck, result);
	}

	/*
	 * updates truck info , only parameters to be updated can be provided
	 * 
	 * @returns updated truck info
	 */
	@Operation(description = "Specified truck id info updated to DB", summary ="Update truck info")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PutMapping("/{truckId}")
	public ResponseEntity<ResponseStructure<Truck>> updateTruck( @RequestBody Truck truck,BindingResult result,@PathVariable int truckId) {
		return service.updateTruck(truckId, truck, result);
	}

	/*
	 * deletes a particular truck from db.
	 */
	@Operation(description = "Specified truck id deleted from DB", summary ="Delete Truck")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("/{truckId}")
	public ResponseEntity<Truck> deleteTruck(@PathVariable int truckId) {
		return service.deleteTruck(truckId);
	}

	/*
	 * @returns list of Truck belonging to a carrier company(based on carrier id) and its count 
	 */
	@Operation(description = "Trucks of specified Carried id is retrieved from DB", summary ="Get all Trucks")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<GetAllTrucksDto>> getAllTruckByCarrierId(@PathVariable int carrierId) {
		return service.getAllTruckByCarrierId(carrierId);
	}
}
