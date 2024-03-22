package com.ff.logisticsmanangement.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
 * occurs when the loading and unloading date is in the past
 */

@AllArgsConstructor
@NoArgsConstructor
public class DateMismatchException extends RuntimeException{
	String message = "";

	@Override
	public String getMessage() {
		return message;
	}
}
