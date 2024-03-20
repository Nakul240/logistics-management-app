package com.ff.logisticsmanangement.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Loading {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loading_seq")
	@SequenceGenerator(name = "loading_seq", sequenceName = "loading_seq", allocationSize = 1, initialValue = 600)
	private int id;
	private String companyName;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@Temporal(TemporalType.DATE)
	private LocalDate loadingDate;
	@Temporal(TemporalType.TIME)
	private LocalTime loadingTime;
}
