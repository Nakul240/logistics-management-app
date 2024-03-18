package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Carrier {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carrier_seq")
	@SequenceGenerator(name = "carrier_seq", sequenceName = "carrier_seq", allocationSize = 1, initialValue = 100)
	private int carrierId;
	private String carrierCompanyName;
	private Long carrierContact;
	private String carrierEmail;
}
