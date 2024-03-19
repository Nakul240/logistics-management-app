package com.ff.logisticsmanangement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.logisticsmanangement.dao.CarrierRepository;
import com.ff.logisticsmanangement.dao.TruckRepository;
import com.ff.logisticsmanangement.dto.GetAllTrucksDto;
import com.ff.logisticsmanangement.dto.ResponseStructure;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Truck;
import com.ff.logisticsmanangement.exception.IdNotFoundException;
import com.ff.logisticsmanangement.util.TruckStatus;

@Service
public class TruckService {

	@Autowired
	private TruckRepository repository;

	@Autowired
	private CarrierRepository carrierRepository;

	// save truck to the database after checking the carrierId presence in the db
	public ResponseEntity<ResponseStructure<Truck>> addTruck(Truck truck) {
		Optional<Carrier> opt = carrierRepository.findById(truck.getCarrierId());
		if (opt.isPresent()) {
			truck.setStatus(TruckStatus.AVAILABLE);
			Truck savedTruck = repository.save(truck);

			ResponseStructure<Truck> structure = new ResponseStructure<Truck>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("Success");
			structure.setData(savedTruck);

			return new ResponseEntity<ResponseStructure<Truck>>(structure, HttpStatus.CREATED);
		} else
			throw new IdNotFoundException("Carrier with the Given Id Not Found");
	}

	// update truck detail accept Truck Status
	public ResponseEntity<ResponseStructure<Truck>> updateTruck(int truckId, Truck truck) {
		Truck fetchedTruck = repository.findById(truckId)
				.orElseThrow(() -> new IdNotFoundException("Truck with the given Id not Found!!"));

		if (truck.getName() != null)
			fetchedTruck.setName(truck.getName());
		if (truck.getRegisteredNumber() != null)
			fetchedTruck.setRegisteredNumber(truck.getRegisteredNumber());
		if (truck.getCapacity() != 0)
			fetchedTruck.setCapacity(truck.getCapacity());
		if (truck.getCarrierId() != 0)
			fetchedTruck.setCarrierId(truck.getCarrierId());

		repository.save(fetchedTruck);

		ResponseStructure<Truck> structure = new ResponseStructure<Truck>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("OK");
		structure.setData(fetchedTruck);

		return new ResponseEntity<ResponseStructure<Truck>>(structure, HttpStatus.OK);
	}

	//delete truck
	public ResponseEntity<Truck> deleteTruck(int truckId) {
		Truck truck = repository.findById(truckId)
				.orElseThrow(() -> new IdNotFoundException("No Truck with the given Id Present"));
		repository.delete(truck);
		return new ResponseEntity<Truck>(HttpStatus.OK);
	}

	//get the list of truck and its count belonging to a carrier company using id
	public ResponseEntity<ResponseStructure<GetAllTrucksDto>> getAllTruckByCarrierId(int carrierId) {

		carrierRepository.findById(carrierId).orElseThrow(()->new IdNotFoundException("Carrier with the Given Id Not Found"));
		List<Truck> trucks = repository.findByCarrierId(carrierId);
		GetAllTrucksDto dto = new GetAllTrucksDto();
		dto.setTrucks(trucks);
		dto.setCountOfTrucks(trucks.size());
		
		ResponseStructure<GetAllTrucksDto> structure = new ResponseStructure<GetAllTrucksDto>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("OK");
		structure.setData(dto);
		
		return new ResponseEntity<ResponseStructure<GetAllTrucksDto>>(structure, HttpStatus.OK);
	}
	

}
