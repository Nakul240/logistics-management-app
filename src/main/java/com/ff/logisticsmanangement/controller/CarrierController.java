package com.ff.logisticsmanangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/logistics/carriers")
public class CarrierController {
	@Autowired
	private CarrierService carrierService;

	// save the carrier details
	@Operation(description = "Carrier details will be saved in the database", summary = "To Create Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier) {
		return carrierService.createCarrier(carrier);
	}

	// get the carrier details by id
	@Operation(description = "Carrier details will be retrived from the database", summary = "To get  Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> getCarrierById(@PathVariable int carrierId) {
		return carrierService.getCarrier(carrierId);
	}

	// update the carrier details
	@Operation(description = "Carrier Details will be updated into the database", summary = "To Update Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(@PathVariable int carrierId,
			@RequestBody Carrier carrier) {
		return carrierService.updateCarrier(carrierId, carrier);
	}

	// delete the carrier details

	@Operation(description = "Carrier will be deleted from the database", summary = "To Delete Carrier Info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "404") })
	@DeleteMapping("/{carrierId}")
	public ResponseEntity<?> deleteCarrier(@PathVariable int carrierId) {

		return carrierService.deleteCarrier(carrierId);
	}

	// get all carrier Details

	@Operation(description = " All Carrier  details will be retrived from the database", summary = "To get all  Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Carrier>>> getAllCarrier() {
		return carrierService.getAllCarrier();
	}
}
