package com.ff.logisticsmanangement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.DriverDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Driver;
import com.ff.logisticsmanangement.service.DriverService;

@RestController
@RequestMapping("/logistics/driver")
public class DriverController {
	
	private DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}
	
	
	@PostMapping("/")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverDto){
		
		return driverService.saveDriver(driverDto);
		
	}
	
	
	@GetMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<Driver>> getDriverById(@PathVariable int driverId){
		
		return driverService.getDriverById(driverId);
		
	}
	
	@PutMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<String>> updateDriver(@PathVariable int driverId, @RequestBody DriverDto driverDto){
		
		return driverService.updateDriver(driverId, driverDto);
		
	}
	
	@DeleteMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<String>> deleteDriver(@PathVariable int driverId){
		return null;
	}
	

}
