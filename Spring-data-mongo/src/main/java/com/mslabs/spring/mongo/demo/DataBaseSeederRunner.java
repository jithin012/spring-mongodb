package com.mslabs.spring.mongo.demo;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mslabs.spring.mongo.demo.db.FlightInformationRepository;
import com.mslabs.spring.mongo.demo.domain.Aircraft;
import com.mslabs.spring.mongo.demo.domain.FlightInformation;
import com.mslabs.spring.mongo.demo.domain.FlightPrinter;
import com.mslabs.spring.mongo.demo.domain.FlightType;

@Component
@Order(1) // first cmdlinerunner
public class DataBaseSeederRunner implements CommandLineRunner {
	private FlightInformationRepository repository; // component execute request to a mongo db

	public DataBaseSeederRunner(FlightInformationRepository repository) {
		this.repository = repository;
	}

	public void run(String... args) throws Exception {
		System.out.println("Seeder start...");

		empty();
		seed();

		long count = this.repository.count();
		System.out.println("Total Flights in db " + count);

		List<FlightInformation> flightInDb = this.repository.findAll(Sort.by("departureCity").ascending());
		FlightPrinter.print(flightInDb);

		System.out.println("Seeder stop...");

	}

	private void seed() {
		// Data
		FlightInformation flightOne = new FlightInformation();
		flightOne.setId("b8b50563-ca90-4afc-9d43-b674892a525c");
		flightOne.setDelayed(false);
		flightOne.setDepartureCity("Rome");
		flightOne.setDestinationCity("Paris");
		flightOne.setDepartureDate(LocalDate.of(2019, 3, 12));
		flightOne.setType(FlightType.International);
		flightOne.setDurationMin(80);
		flightOne.setAircraft(new Aircraft("737", 180));
		flightOne.setDescription("Flight from Rome to Paris");

		FlightInformation flightTwo = new FlightInformation();
		flightTwo.setId("c0725fbb-eebb-4aae-8b41-3887793d0c50");
		flightTwo.setDelayed(false);
		flightTwo.setDepartureCity("New York");
		flightTwo.setDestinationCity("Copenhagen");
		flightTwo.setDepartureDate(LocalDate.of(2019, 5, 11));
		flightTwo.setType(FlightType.International);
		flightTwo.setDurationMin(600);
		flightTwo.setAircraft(new Aircraft("747", 300));
		flightTwo.setDescription("Flight from NY to Copenhagen via Rome");

		FlightInformation flightThree = new FlightInformation();
		flightThree.setId("bd8bc9ab-3bdc-4f57-8871-0c43501dc5c6");
		flightThree.setDelayed(true);
		flightThree.setDepartureCity("Bruxelles");
		flightThree.setDestinationCity("Bucharest");
		flightThree.setDepartureDate(LocalDate.of(2019, 6, 12));
		flightThree.setType(FlightType.International);
		flightThree.setDurationMin(150);
		flightThree.setAircraft(new Aircraft("A320", 170));

		FlightInformation flightFour = new FlightInformation();
		flightFour.setId("73f478e5-cb3d-415d-ae20-e37f0df3231d");
		flightFour.setDelayed(true);
		flightFour.setDepartureCity("Madrid");
		flightFour.setDestinationCity("Barcelona");
		flightFour.setDepartureDate(LocalDate.of(2019, 6, 12));
		flightFour.setType(FlightType.Internal);
		flightFour.setDurationMin(120);
		flightFour.setAircraft(new Aircraft("A319", 150));

		FlightInformation flightFive = new FlightInformation();
		flightFive.setId("51cd0e4f-1e42-4e48-a961-64f889eee6b5");
		flightFive.setDelayed(false);
		flightFive.setDepartureCity("Las Vegas");
		flightFive.setDestinationCity("Washington");
		flightFive.setDepartureDate(LocalDate.of(2019, 6, 10));
		flightFive.setType(FlightType.Internal);
		flightFive.setDurationMin(400);
		flightFive.setAircraft(new Aircraft("A319", 150));
		flightTwo.setDescription("Flight from LA to Washington via Paris");

		FlightInformation flightSix = new FlightInformation();
		flightSix.setId("4d23fd8b-47a7-45f8-958c-94d0e21488b2");
		flightSix.setDelayed(false);
		flightSix.setDepartureCity("Bucharest");
		flightSix.setDestinationCity("Rome");
		flightSix.setDepartureDate(LocalDate.of(2019, 6, 13));
		flightSix.setType(FlightType.International);
		flightSix.setDurationMin(110);
		flightSix.setAircraft(new Aircraft("A321 Neo", 200));

		// Seed
		List<FlightInformation> flights = Arrays.asList(flightOne, flightTwo, flightThree, flightFour, flightFive,
				flightSix);
		repository.insert(flights); // batch insert
	}

	private void empty() {
		repository.deleteAll();
	}

}
