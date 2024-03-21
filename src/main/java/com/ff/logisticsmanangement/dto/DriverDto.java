package com.ff.logisticsmanangement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
	
	
	@NotBlank(message = "driver Name cannot be null.")
	private String driverName;
	
	@NotNull
	@Min(value = 1000000000L, message = "Phone number must be at least 10 digits long")
	private long driverPhoneNumber;
	
	private String truckRegisterNumber;
	
	@NotNull(message = "proper carrier id needed")
	private int carrierId;
}
