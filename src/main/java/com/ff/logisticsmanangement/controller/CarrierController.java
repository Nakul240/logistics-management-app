package com.ff.logisticsmanangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/logistics/carriers")
public class CarrierController {
	@Autowired
	private CarrierService carrierService;

	/*
	 * saves carrier company details into db
	 * 
	 * @returns saved carrier info
	 */
	@Operation(description = "Carrier details will be saved in the database", summary = "To Create Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@Valid @RequestBody Carrier carrier, BindingResult result) {
		return carrierService.createCarrier(carrier, result);
	}

	/*
	 * @returns carrier info based on id provided
	 */
	@Operation(description = "Carrier details will be retrived from the database", summary = "To get  Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> getCarrierById(@PathVariable int carrierId) {
		return carrierService.getCarrier(carrierId);
	}

	/*
	 * updates particular carrier based on id , any parameter can be updated
	 * 
	 * @returns updated carrier info
	 */
	@Operation(description = "Carrier Details will be updated into the database", summary = "To Update Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PutMapping("/{carrierId}")
	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(@PathVariable int carrierId,
			@Valid @RequestBody Carrier carrier, BindingResult bindingResult) {
		return carrierService.updateCarrier(carrierId, carrier,bindingResult);
	}

	/*
	 * deletes a particular carried based on id
	 */
	@Operation(description = "Carrier will be deleted from the database", summary = "To Delete Carrier Info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "404") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("/{carrierId}")
	public ResponseEntity<?> deleteCarrier(@PathVariable int carrierId) {

		return carrierService.deleteCarrier(carrierId);
	}

	/*
	 * @returns List of all the Carriers present in the db
	 */
	@Operation(description = " All Carrier  details will be retrived from the database", summary = "To get all  Carrier info")
	@ApiResponses(value = { @ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Carrier>>> getAllCarrier() {
		return carrierService.getAllCarrier();
	}
}
