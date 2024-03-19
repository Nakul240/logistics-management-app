package com.ff.logisticsmanangement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IdNotFoundException extends RuntimeException{
	String message = "";

	@Override
	public String getMessage() {
		return message;
	}
}
