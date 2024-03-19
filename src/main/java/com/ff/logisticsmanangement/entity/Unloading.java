package com.ff.logisticsmanangement.entity;

import java.sql.Date;
import java.sql.Time;

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
public class Unloading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "unloading_id")
	@SequenceGenerator(name = "unloading_id", initialValue = 800, allocationSize = 1, sequenceName = "unloading_sequence")
	private int UnloadingId;
	private String companyName;
	@OneToOne
	private Address address;
	@Temporal(TemporalType.DATE)
	private Date unloadingDate;
	@Temporal(TemporalType.TIME)
	private Time unloadingTime;

}
