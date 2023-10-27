package com.example.securityinaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SecurityInActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityInActionApplication.class, args);
	}

}
