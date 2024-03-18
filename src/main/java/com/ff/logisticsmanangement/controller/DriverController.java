package com.ff.logisticsmanangement.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.service.DriverService;

@RestController
public class DriverController {
	
	private DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

}
