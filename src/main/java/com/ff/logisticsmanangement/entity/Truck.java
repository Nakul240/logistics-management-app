package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
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
	private String registerdNumber;
	private double capacity;
	private String status;
	private int carrierId;
	
}
