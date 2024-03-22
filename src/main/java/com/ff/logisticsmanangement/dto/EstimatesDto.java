package com.ff.logisticsmanangement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Dto class created to store distance and duration return by service methods
 * which uses graphhopper api
 * 
 * also includes frieghtCost and duration converted into days based on requirement of shippment
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimatesDto {
	
	private double distance;
	private long time;
	
	private double frieghtCost;
	private int days;

}
