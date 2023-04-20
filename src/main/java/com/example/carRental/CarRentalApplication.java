package com.example.carRental;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
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
			
			KafkaProducer<String, String> producer = new KafkaProducer<>(setProperties());

			for (Car c : carRepository.findAll()) {
				// create a producer record
				ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic", c.toString());
				
				// send data - asynchronous
				producer.send(producerRecord);
				System.out.println(c.toString());
			}
			// flush data - synchronous
			producer.flush();
        
			// flush and close producer
			producer.close();

		};
	}

	public Properties setProperties(){

        String bootstrapServers = "127.0.0.1:9092";

		// create Producer properties
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}

}
