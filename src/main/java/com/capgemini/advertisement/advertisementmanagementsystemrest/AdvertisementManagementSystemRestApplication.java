package com.capgemini.advertisement.advertisementmanagementsystemrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.capgemini.advertisement")
@EntityScan(basePackages = "com.capgemini.advertisement.entity")
@EnableJpaRepositories(basePackages = "com.capgemini.advertisement.dao")
public class AdvertisementManagementSystemRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisementManagementSystemRestApplication.class, args);
	}

}
