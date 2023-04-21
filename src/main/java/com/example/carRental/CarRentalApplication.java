package com.example.carRental;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
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

			Car car1 = new Car();
			car1.setPlateNumber("AA11BB");
			Car car2 = new Car();
			car2.setPlateNumber("ZZ99YY");
			Car car3 = new Car();
			car3.setPlateNumber("GL46EU");
			ArrayList<Car> Cars = new ArrayList<>();
			Cars.add(car1);
			Cars.add(car2);
			Cars.add(car3);
			carRepository.saveAll(Cars);

			System.out.println("-------------------------------");
			System.out.println("Car found with findAll():");
			KafkaProducer<String, String> producer;
			producer = new KafkaProducer<>(setProperties());

			for (Car c : carRepository.findAll()) {
				
				System.out.println(c.toString());// create a producer record
				
				ProducerRecord<String, String> producerRecord = new ProducerRecord<>("quickstart", c.toString());
				
				// send data - asynchronous
				producer.send(producerRecord);
			}


			// flush data - synchronous
			producer.flush();
        
			// flush and close producer
			producer.close();

		};
	}

	public Properties setProperties(){

        String bootstrapServers = "broker:9092";

		// create Producer properties
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
