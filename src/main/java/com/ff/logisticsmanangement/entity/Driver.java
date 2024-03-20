package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "driverIdGenerator")
	@SequenceGenerator(name = "driverIdGenerator", allocationSize = 1, initialValue = 300)
	private int id;

	private String driverName;

	private long driverPhoneNumber;

	// truckRegisterNumber

	private String truckRegisterNumber;

	@ManyToOne
	@JoinColumn(name = "carrier")
	private Carrier carrier;

}
