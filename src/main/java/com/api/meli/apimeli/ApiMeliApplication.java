package com.api.meli.apimeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ApiMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMeliApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
