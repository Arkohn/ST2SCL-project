package com.example.carRental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CarRepository carRepository) {
		return (args) -> {

			Car car = new Car();
			car.setPlateNumber("AA11BB");
			carRepository.save(car);

			System.out.println("-------------------------------");
			System.out.println("Car found with findAll():");
			for (Car c : carRepository.findAll()) {
				System.out.println(c.toString());
			}

		};
	}

}
