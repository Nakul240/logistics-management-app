package com.ff.logisticsmanangement.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.EstimatesDto;
import com.ff.logisticsmanangement.service.DistanceAndDurationEstimationService;

@RestController
public class DistanceAndDurationEstimationController {

	@Autowired
	private DistanceAndDurationEstimationService service;
	
	@GetMapping("/estimate")
	public EstimatesDto demo(@RequestParam String loadingAddress, @RequestParam String unloadingAddress) throws ParseException {
		
		return service.getTheDistanceAndTime(loadingAddress, unloadingAddress);
	}
}

