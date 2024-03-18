package com.ff.logisticsmanangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dao.DriverRepository;
import com.ff.logisticsmanangement.dto.DriverDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Driver;

@Service
public class DriverService {
	
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	
	
	public ResponseEntity<ResponseStructure<Driver>> saveDriver(DriverDto driverDto){
		
		Carrier carrier = carrierRepository.findById(driverDto.getCarrierId()).orElse(null);
		
		if(carrier != null) {

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
		
		// throw carrier id not valid exception here
		return null;
		
	}
	
	

}
