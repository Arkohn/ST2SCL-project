package com.example.carRental;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		String generateTopic = "docker exec --interactive --tty kafka-broker kafka-topics --bootstrap-server kafka:9092 --create --topic quickstart";
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", generateTopic);
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			
			List<String> Producer = new ArrayList<String>();
			Producer.add("cmd.exe");
			Producer.add("/c");
			Producer.add("docker exec --interactive --tty kafka-broker kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart");

			for (Car c : carRepository.findAll()) {
				Producer.add(c.toString());
				System.out.println(c.toString());
			}
			
			String[] Prodstr = (String[]) Producer.toArray();
			
			ProcessBuilder pb = new ProcessBuilder(Prodstr);
			try {
				pb.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		};
	}

}
