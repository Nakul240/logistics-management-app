package com.ff.logisticsmanangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Truck;
import com.ff.logisticsmanangement.service.TruckService;


@RestController
@RequestMapping("/logistics/truck")
public class TruckController {

	@Autowired
	private TruckService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Truck>> addTuck(@RequestBody Truck truck){
		return service.addTruck(truck);
	}
}
