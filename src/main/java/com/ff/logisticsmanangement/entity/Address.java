package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "user_id", initialValue = 400, allocationSize = 1, sequenceName = "user_sequence")
	private int addressId;
	private String streetName;
	private int houseNumber;
	private int areaPinCode;
	private String district;
	private String state;
	private String country;

}
