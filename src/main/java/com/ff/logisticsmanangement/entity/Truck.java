package com.ff.logisticsmanangement.entity;

import com.ff.logisticsmanangement.util.TruckStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Truck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "truckIdGenerator")
	@SequenceGenerator(name = "truckIdGenerator",initialValue = 200,allocationSize = 1)
	private int id;
	private String name;
	private String registeredNumber;
	private double capacity;
	
	@Enumerated(EnumType.STRING)
	private TruckStatus status;
	private int carrierId;
	
}
