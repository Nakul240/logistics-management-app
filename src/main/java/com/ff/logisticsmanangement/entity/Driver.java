package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String driverName;
	
	private long driverPhoneNumber;
	
	// truckRegisterNumber
	
	private String truckRegisterNumber;
	
	@ManyToOne
	@JoinColumn
	private Carrier carrier;

}
