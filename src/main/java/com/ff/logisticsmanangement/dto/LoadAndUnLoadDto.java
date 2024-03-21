package com.ff.logisticsmanangement.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoadAndUnLoadDto {
	
	@NotNull(message = "list of user cannot be empty!")
	private List<Integer> userIds;

}
