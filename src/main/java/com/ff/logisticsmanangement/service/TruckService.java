package com.ff.logisticsmanangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.TruckRepository;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Truck;

@Service
public class TruckService {

	@Autowired
	private TruckRepository repository;
	
	public ResponseEntity<ResponseStructure<Truck>> addTruck(Truck truck) {
		Truck savedTruck = repository.save(truck);
		
		
		
		return null;
	}

}
