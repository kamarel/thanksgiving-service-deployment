package com.thanksgiving.thanksgivingservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@OpenAPIDefinition(
		info = @Info(
				title = "Thanksgiving Service Rest APIS Documentation",
				description = "ThanksgivingService Rest Apis it is part the myKcc microservice",
				version = "3.3.1",
				contact = @Contact(
						name = "myKcc-Project",
						email = "kamarelngunda@gmail.com",
						url = "www.myKcc.com"
				),
				license = @License(
						name = "Apache 3.0",
						url = "www.myKcc.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Thanksgiving-Service-docs",
				url = "http://www.myKcc.com"
		)
)
@SpringBootApplication
public class ThanksgivingServiceApplication {

	@Value("${gateway.security.key}") // Make sure this key is configured in your application properties
	private String gatewaySecurityKey;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.defaultHeader("X-Requested-By", gatewaySecurityKey) // Set the API key in the headers
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ThanksgivingServiceApplication.class, args);
	}

}
