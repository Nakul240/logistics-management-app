package com.ff.logisticsmanangement.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * used to contain the list of user id of loading or unloading users of an order
 */

@Data
public class LoadAndUnLoadDto {
	
	@NotNull(message = "list of user cannot be empty!")
	private List<Integer> userIds;

}
