package ch.juventus.skiservice;

import ch.juventus.skiservice.data.ServiceOrder;
import ch.juventus.skiservice.data.ServiceOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(ServiceOrderRepository repository) {
		return (args) -> {
			// save a couple of customers7
			java.util.Date date = new java.util.Date();
			//repository.save(new ServiceOrder("Miguel", "m@m.ch", "0000", "Tief", "PENDENT", "Kleiner Service", "", ""));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (ServiceOrder serviceOrder : repository.findAll()) {
				log.info(serviceOrder.toString());
			}
			log.info("");

			// fetch an individual serviceOrder by ID
			//ServiceOrder serviceOrder = repository.findById(1L).get();
			log.info("ServiceOrder found with findOne(1L):");
			log.info("--------------------------------");
			//log.info(serviceOrder.toString());
			log.info("");

			// fetch customers by last name
			log.info("ServiceOrder found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");

			log.info("");
		};
	}

}
