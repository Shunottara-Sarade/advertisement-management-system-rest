package com.capgemini.advertisement.advertisementmanagementsystemrest;

import java.util.function.Predicate;


import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@EnableOpenApi
@SpringBootApplication(scanBasePackages = "com.capgemini.advertisement")
@EntityScan(basePackages = "com.capgemini.advertisement.entity")
@EnableJpaRepositories(basePackages = "com.capgemini.advertisement.dao")
public class AdvertisementManagementSystemRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisementManagementSystemRestApplication.class, args);
	}
	@Bean
	  public Docket openApiStaffStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-staff-store")
	        .select()
	        .paths(staffPaths())
	        .build();
	  }

	  private Predicate<String> staffPaths() {
	    return regex(".*/api/staff/.*");
	  }
	  
	  @Bean
	  public Docket openApiCustomerStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-customer-store")
	        .select()
	        .paths(customerPaths())
	        .build();
	  }

	  private Predicate<String> customerPaths() {
	    return regex(".*/api/customers/.*");
	  }
	  
	  @Bean
	  public Docket openApiAdvertisementStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-advertisement-store")
	        .select()
	        .paths(advertisementPaths())
	        .build();
	  }

	  private Predicate<String> advertisementPaths() {
	    return regex(".*/api/advertisement/.*");
	  }
	  
	  @Bean
	  public Docket openApiStaffLogin() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-staff-login")
	        .select()
	        .paths(staffLoginPaths())
	        .build();
	  }

	  private Predicate<String> staffLoginPaths() {
	    return regex(".*/api/staffLogin/.*");
	  }
	  
	  @Bean
	  public Docket openApiCustomerLogin() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-customer-login")
	        .select()
	        .paths(customerLoginPaths())
	        .build();
	  }

	  private Predicate<String> customerLoginPaths() {
	    return regex(".*/api/customerLogin/.*");
	  }
}



