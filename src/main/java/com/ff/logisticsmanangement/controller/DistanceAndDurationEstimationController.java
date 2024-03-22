package com.ff.logisticsmanangement.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.EstimatesDto;
import com.ff.logisticsmanangement.service.DistanceAndDurationEstimationService;

@RestController
@RequestMapping("/logistics")
public class DistanceAndDurationEstimationController {

	@Autowired
	private DistanceAndDurationEstimationService service;
	
	/*
	 * demo api to get the distance and duration between to address 
	 * 
	 * addresses will be converted into lattitudes and longitudes to fetch the result
	 */
	@GetMapping("/estimate")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public EstimatesDto demo(@RequestParam String loadingAddress, @RequestParam String unloadingAddress) throws ParseException {
		
		return service.getTheDistanceAndTime(loadingAddress, unloadingAddress);
	}
}

