package com.ff.logisticsmanangement.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ff.logisticsmanangement.util.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1, initialValue = 800)
	private int id;
	@CreationTimestamp
	private LocalDate dateOfOrder;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private Double freightCost;
	private String additionalInfo;

	@ManyToOne
	private Carrier carrier;

	@OneToOne(cascade = CascadeType.ALL)
	private Cargo cargo;

	@OneToOne(cascade = CascadeType.ALL)
	private Loading loading;

	@OneToOne(cascade = CascadeType.ALL)
	private Unloading unloading;

	@ManyToMany
	@JsonIgnore
	private List<User> loadingUser;

	@ManyToMany
	@JsonIgnore
	private List<User> unloadingUser;

}
