package com.ff.logisticsmanangement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DateMismatchException extends RuntimeException{
	String message = "";

	@Override
	public String getMessage() {
		return message;
	}
}
