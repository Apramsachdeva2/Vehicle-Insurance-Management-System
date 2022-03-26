package com.cts.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cts")
public class VehicleInsuranceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleInsuranceManagementSystemApplication.class, args);
	}

}
