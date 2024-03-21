package com.ff.logisticsmanangement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Carrier {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carrier_seq")
	@SequenceGenerator(name = "carrier_seq", sequenceName = "carrier_seq", allocationSize = 1, initialValue = 100)
	private int id;

	@NotBlank(message = "carrier company name cannot be blank")
	private String carrierCompanyName;

	@NotNull
	@Min(value = 1000000000L, message = "Phone number must be at least 10 digits long")
	private Long carrierContact;

	@Email
	@NotNull
	private String carrierEmail;

	@OneToMany(mappedBy = "carrier")
	@JsonIgnore
	private List<Driver> driver;

}
