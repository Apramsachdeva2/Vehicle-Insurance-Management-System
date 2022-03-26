package com.cts.vehicle.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String handler(Exception e)
	{
		System.out.println(e.getMessage());
		return "error";
	}
}
