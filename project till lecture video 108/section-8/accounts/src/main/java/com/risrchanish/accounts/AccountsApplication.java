package com.risrchanish.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice REST API documentation",
				description = "RisrchAnish Accounts microservice documentation",
				version = "v1",
				contact = @Contact(
						name = "Anish Kumar",
						email = "risrchanish@gmail.com",
						url = "https://github.com/risrchanish"
						)
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		System.out.println("App is running..");
	}

}
