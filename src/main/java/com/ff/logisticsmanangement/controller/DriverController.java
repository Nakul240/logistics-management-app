package com.ff.logisticsmanangement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.DriverDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Driver;
import com.ff.logisticsmanangement.service.DriverService;

@RestController
public class DriverController {
	
	private DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}
	
	
	@PostMapping("/driver")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverDto){
		
		return driverService.saveDriver(driverDto);
		
	}
	
	
	@GetMapping("/driver/{driverId}")
	public ResponseEntity<ResponseStructure<Driver>> getDriverById(@PathVariable int driverId){
		
		return driverService.getDriverById(driverId);
		
	}

}
