package com.github.kaydunov.dashboardeleven;

import com.github.kaydunov.dashboardeleven.entity.Order;
import com.github.kaydunov.dashboardeleven.handler.SupplierServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	private static final Logger log = LoggerFactory.getLogger(SupplierServiceHandler.class);


//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
//
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) {
//		return args -> {
//			Order value = restTemplate.getForObject("http://localhost:8081/odata/v4/OrderService/Orders(1)", Order.class);
//			if (Objects.nonNull(value)) {
//				log.info(restTemplate.toString());
//				log.info(value.toString());
//				log.info("!!!!!!!!!!!!!!!!!!!");
//			}
//			else log.info("Quote is null!!!!!!!!");
//		};
//	}
}
