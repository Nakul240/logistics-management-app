package com.ff.logisticsmanangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.service.CarrierService;

@RestController
@RequestMapping("/logistics")
public class CarrierController {
	@Autowired
	private CarrierService carrierService;

//save the carrier details
	@PostMapping("/carriers")
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier) {
		return carrierService.createCarrier(carrier);
	}

//get the carrier details by id
	@GetMapping("/carriers/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> getCarrierById(@PathVariable int carrierId) {
		return carrierService.getCarrier(carrierId);
	}

//update the carrier details
	@PutMapping("/carriers/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(@PathVariable int carrierId,
			@RequestBody Carrier carrier) {
		return carrierService.updateCarrier(carrierId, carrier);
	}

}
