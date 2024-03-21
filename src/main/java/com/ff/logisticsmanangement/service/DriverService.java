package com.ff.logisticsmanangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dao.DriverRepository;
import com.ff.logisticsmanangement.dto.DriverDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Driver;
import com.ff.logisticsmanangement.exception.IdNotFoundException;

@Service
public class DriverService {

	@Autowired
	private CarrierRepository carrierRepository;

	@Autowired
	private DriverRepository driverRepository;

	public ResponseEntity<ResponseStructure<Driver>> saveDriver(DriverDto driverDto, BindingResult result) {
		
		if (result.hasErrors()) {

			String message = "";

			for (FieldError error : result.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}

		Carrier carrier = carrierRepository.findById(driverDto.getCarrierId()).orElseThrow(()-> new IdNotFoundException("Carrier not found"));

		Driver driver = new Driver();
		driver.setCarrier(carrier);
		driver.setDriverPhoneNumber(driverDto.getDriverPhoneNumber());
		driver.setDriverName(driverDto.getDriverName());
		driver.setTruckRegisterNumber(driverDto.getTruckRegisterNumber());

		driver = driverRepository.save(driver);

		ResponseStructure<Driver> rs = new ResponseStructure<>();
		rs.setData(driver);
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Driver>>(rs, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Driver>> getDriverById(int id) {

		Driver driver = driverRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Driver not found with "+ id));
		
		ResponseStructure<Driver> rs = new ResponseStructure<>();
		rs.setData(driver);
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Driver>>(rs, HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<String>> updateDriver(int id, DriverDto driverDto, BindingResult result){
		
		if (result.hasErrors()) {

			String message = "";

			for (FieldError error : result.getFieldErrors()) {

				message += error.getDefaultMessage() + " ,";

			}
			throw new IdNotFoundException(message);
		}
		
		Driver driver = driverRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Driver not found with "+ id));
		
		driver.setDriverName(driverDto.getDriverName());
		driver.setDriverPhoneNumber(driverDto.getDriverPhoneNumber());
		driver.setTruckRegisterNumber(driverDto.getTruckRegisterNumber());
		
		driver = driverRepository.save(driver);
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData("Driver Updated Successfully!");
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteDriver(int id){
		
		driverRepository.deleteById(id);
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData("Driver Deleted Successfully!");
		rs.setMessage("Success");
		rs.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

}
