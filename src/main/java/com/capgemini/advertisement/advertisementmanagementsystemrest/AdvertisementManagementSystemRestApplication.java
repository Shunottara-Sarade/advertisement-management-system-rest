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
	 /**
     * @return Docket
     */
	@Bean
	  public Docket openApiStaffStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-staff-store")
	        .select()
	        .paths(staffPaths())
	        .build();
	  }
	/**
	 * 
	 * @return regex
	 */

	  private Predicate<String> staffPaths() {
	    return regex(".*/api/staff/.*");
	  }
	  
	  /**
	     * @return Docket
	     */
	  @Bean
	  public Docket openApiCustomerStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-customer-store")
	        .select()
	        .paths(customerPaths())
	        .build();
	  }
	  /**
	   * 
	   * @return regex
	   */
	  private Predicate<String> customerPaths() {
	    return regex(".*/api/customers/.*");
	  }
	  /**
	   * 
	   * @return Docket
	   */
	  @Bean
	  public Docket openApiAdvertisementStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-advertisement-store")
	        .select()
	        .paths(advertisementPaths())
	        .build();
	  }
	  /**
	   * 
	   * @return regex
	   */

	  private Predicate<String> advertisementPaths() {
	    return regex(".*/api/advertisement/.*");
	  }
	  /**
	   * 
	   * @return Docket
	   */
	  @Bean
	  public Docket openApiStaffLogin() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-staff-login")
	        .select()
	        .paths(staffLoginPaths())
	        .build();
	  }
	  /**
	   * 
	   * @return regex
	   */
	  private Predicate<String> staffLoginPaths() {
	    return regex(".*/api/staffLogin/.*");
	  }
	  
	  /**
	   * 
	   * @return Docket
	   */
	  @Bean
	  public Docket openApiCustomerLogin() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-customer-login")
	        .select()
	        .paths(customerLoginPaths())
	        .build();
	  }
	  /**
	   * 
	   * @return regex
	   */

	  private Predicate<String> customerLoginPaths() {
	    return regex(".*/api/customerLogin/.*");
	  }
}



