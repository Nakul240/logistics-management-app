package com.ff.logisticsmanangement.entity;

import com.ff.logisticsmanangement.util.TruckStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "Truck name should not be blank")
	private String name;
	
	@NotBlank
	@Size(min = 10, message = "invalid Registration number. length should be atleast 10")
	private String registeredNumber;
	
	@PositiveOrZero
	private double capacity;
	
	@Enumerated(EnumType.STRING)
	private TruckStatus status;
	
	@NotNull
	private int carrierId;
	
}
