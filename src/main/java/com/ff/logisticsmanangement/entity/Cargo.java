package com.ff.logisticsmanangement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cargo_id")
	@SequenceGenerator(name = "cargo_id", initialValue = 700, allocationSize = 1, sequenceName = "cargo_sequence")
	private int id;
	private String cargoName;
	private String cargoDescription;
	private double cargoWeight;
	private int cargoCount;

}
