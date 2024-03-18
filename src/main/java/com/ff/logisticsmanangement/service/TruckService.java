package com.ff.logisticsmanangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.TruckRepository;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Truck;
import com.ff.logisticsmanangement.util.TruckStatus;

@Service
public class TruckService {

	@Autowired
	private TruckRepository repository;
	
	public ResponseEntity<ResponseStructure<Truck>> addTruck(Truck truck) {
		truck.setStatus(TruckStatus.AVAILABLE);
		Truck savedTruck = repository.save(truck);
		
		ResponseStructure<Truck> structure = new ResponseStructure<Truck>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedTruck);

		return new ResponseEntity<ResponseStructure<Truck>>(structure, HttpStatus.CREATED);
	}

}
