package com.ff.logisticsmanangement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.exception.IdNotFoundException;

@Service
public class CarrierService {
	@Autowired
	private CarrierRepository carrierRespository;

	public ResponseEntity<ResponseStructure<Carrier>> createCarrier(Carrier carrier, BindingResult result) {

		if (result.hasErrors()) {

			String message = "";

			for (FieldError error : result.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}

		Carrier savedCarrier = carrierRespository.save(carrier);
		ResponseStructure<Carrier> response = new ResponseStructure<Carrier>();
		response.setMessage("success");
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setData(savedCarrier);

		return new ResponseEntity<ResponseStructure<Carrier>>(response, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Carrier>> getCarrier(int id) {
		Optional<Carrier> carrier = carrierRespository.findById(id);
		if (carrier.isPresent()) {
			Carrier recievedCarrier = carrier.get();
			ResponseStructure<Carrier> response = new ResponseStructure<Carrier>();
			response.setMessage("success");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(recievedCarrier);
			return new ResponseEntity<ResponseStructure<Carrier>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found..!");
		}
	}

	public ResponseEntity<ResponseStructure<Carrier>> updateCarrier(int id, Carrier carrier,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			String message = "";

			for (FieldError error : bindingResult.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}

		Optional<Carrier> option = carrierRespository.findById(id);
		if (option.isPresent()) {
			Carrier recievedCarrier = option.get();
			if (carrier.getCarrierCompanyName() != null) {
				recievedCarrier.setCarrierCompanyName(carrier.getCarrierCompanyName());
			}
			if (carrier.getCarrierEmail() != null) {
				recievedCarrier.setCarrierEmail(carrier.getCarrierEmail());
			}
			if (carrier.getCarrierContact() != null) {
				recievedCarrier.setCarrierContact(carrier.getCarrierContact());
			}
			carrierRespository.save(recievedCarrier);
			ResponseStructure<Carrier> response = new ResponseStructure<Carrier>();
			response.setMessage("OK");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(recievedCarrier);
			return new ResponseEntity<ResponseStructure<Carrier>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("CarrierId not found..!");
		}
	}

	public ResponseEntity<String> deleteCarrier(int id) {
		Optional<Carrier> carrier = carrierRespository.findById(id);
		if (carrier.isPresent()) {
			carrierRespository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			throw new IdNotFoundException("CarrierId not found..!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Carrier>>> getAllCarrier() {
		List<Carrier> carrierList = carrierRespository.findAll();
		ResponseStructure<List<Carrier>> response = new ResponseStructure<List<Carrier>>();
		response.setMessage("success");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(carrierList);
		return new ResponseEntity<ResponseStructure<List<Carrier>>>(response, HttpStatus.OK);

	}

}