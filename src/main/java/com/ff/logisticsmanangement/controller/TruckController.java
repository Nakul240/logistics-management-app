package com.ff.logisticsmanangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


@RestController
@RequestMapping("/logistics/trucks")
public class TruckController {

	@Autowired
	private TruckService service;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<ResponseStructure<Truck>> addTruck(@RequestBody Truck truck){
		return service.addTruck(truck);
	}
	
	@PutMapping("/{truckId}")
	public ResponseEntity<ResponseStructure<Truck>> updateTruck(@PathVariable int truckId,@RequestBody Truck truck){
		return service.updateTruck(truckId,truck);
	}
	
	@DeleteMapping("/{truckId}")
	public ResponseEntity<Truck> deleteTruck(@PathVariable int truckId) {
		return service.deleteTruck(truckId);
	}
	
	@GetMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<GetAllTrucksDto>> getAllTruckByCarrierId(@PathVariable int carrierId){
		return service.getAllTruckByCarrierId(carrierId);
	}
}
