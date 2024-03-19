package com.ff.logisticsmanangement.dto;

import java.util.List;

import com.ff.logisticsmanangement.entity.Truck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllTrucksDto {

	private int countOfTrucks;
	private List<Truck> trucks;
}
