package com.ff.logisticsmanangement.controller;

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

import com.ff.logisticsmanangement.dto.DriverDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Driver;
import com.ff.logisticsmanangement.service.DriverService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/logistics/drivers")
public class DriverController {
	
	private DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}
	
	
	@Operation(description = "To save driver information", summary = "saving driver information" )
	@ApiResponses(value = {@ApiResponse(description = "Created", responseCode = "201", content = @Content)})
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverDto){
		
		return driverService.saveDriver(driverDto);
		
	}
	
	@Operation(description = "To get a driver through specific driver Id", summary = "get a driver" )
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200", content = @Content)})
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<Driver>> getDriverById(@PathVariable int driverId){
		
		return driverService.getDriverById(driverId);
		
	}
	
	@Operation(description = "To update driver info through specific driver Id in the DB", summary = "Update driver info" )
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200", content = @Content)})
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
	@PutMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<String>> updateDriver(@PathVariable int driverId, @RequestBody DriverDto driverDto){
		
		return driverService.updateDriver(driverId, driverDto);
		
	}
	
	@Operation(description = "To delete a driver through specific driver Id", summary = "delete a driver" )
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200", content = @Content)})
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("/{driverId}")
	public ResponseEntity<ResponseStructure<String>> deleteDriver(@PathVariable int driverId){
		return null;
	}
	

}
