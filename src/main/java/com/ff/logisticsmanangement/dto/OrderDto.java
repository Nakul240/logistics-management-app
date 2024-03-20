package com.ff.logisticsmanangement.dto;

import com.ff.logisticsmanangement.entity.Cargo;
import com.ff.logisticsmanangement.entity.Carrier;
import com.ff.logisticsmanangement.entity.Loading;
import com.ff.logisticsmanangement.entity.Unloading;
import com.ff.logisticsmanangement.util.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {
	private String additionalInfo;
	private Carrier carrier;
	private Cargo cargo;
	private Loading loading;
	private Unloading unloading;
}
