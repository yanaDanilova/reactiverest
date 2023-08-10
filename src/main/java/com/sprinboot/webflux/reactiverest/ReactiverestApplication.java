package com.sprinboot.webflux.reactiverest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "WebFluxApp", version = "1.0", description = "Documentation WebFluxApp v1.0"))
@SpringBootApplication
public class ReactiverestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiverestApplication.class, args);
	}

}
