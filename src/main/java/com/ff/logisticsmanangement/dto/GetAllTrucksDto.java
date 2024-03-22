package com.ff.logisticsmanangement.dto;

import java.util.List;

import com.ff.logisticsmanangement.entity.Truck;

import lombok.Getter;
import lombok.Setter;

/*
 * used to return count of Trucks and the list of trucks of a particular carrier company
 */

@Getter
@Setter
public class GetAllTrucksDto {

	private int countOfTrucks;
	private List<Truck> trucks;
}
