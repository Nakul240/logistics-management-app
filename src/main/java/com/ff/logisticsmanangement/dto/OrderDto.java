package com.ff.logisticsmanangement.dto;

import com.ff.logisticsmanangement.entity.Cargo;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Loading;
import com.ff.logisticsmanangement.entity.Unloading;
import com.ff.logisticsmanangement.util.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
	
	private String additionalInfo;
	private int carrierId;
	private Cargo cargo;
	private Loading loading;
	private Unloading unloading;
}
