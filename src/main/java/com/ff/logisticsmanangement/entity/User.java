package com.ff.logisticsmanangement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ff.logisticsmanangement.util.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Builder
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
	@SequenceGenerator(name = "user_id", initialValue = 1, allocationSize = 1, sequenceName = "user_sequence")
	private int userId;
	private String userName;
	@JsonIgnore
	private String userPassword;
	private Long userPhoneNumber;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@Enumerated(EnumType.STRING)
	private Role userRole;

}
