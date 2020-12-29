package com.capgemini.advertisement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.capgemini.advertisement.controller.CustomerController;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.service.CustomerServiceSpringDataImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.springframework.test.web.servlet.MvcResult;
@WebMvcTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomerController.class})
@WebAppConfiguration
class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerServiceSpringDataImpl customerService;

	private static ObjectMapper mapper = new ObjectMapper();
	private final Integer custId=1;
	private final String firstName="Virat";
	private final String LastName="Kohli";
	private final String email="virat@gmail.com";
	private final String mobile="9852123265";
	private final String password="virat@123";


	@Test
	void testGetCustomerById() throws Exception {
		CustomerMaster customerMaster=new CustomerMaster();
		customerMaster.setCustId(custId);
		customerMaster.setCustFirstName(firstName);
		customerMaster.setCustLastName(LastName);
		customerMaster.setCustEmail(email);
		customerMaster.setCustMobile(mobile);
		customerMaster.setCustPassword(password);
		Mockito.when(customerService.getCustomerById(custId)).thenReturn(customerMaster);

		// Execute the GET request
		mockMvc.perform(get("/api/customers/1"))

		// Validate the response code
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$.custId",is(customerMaster.getCustId())))
		.andExpect(jsonPath("$.custFirstName", is(customerMaster.getCustFirstName())))
		.andExpect(jsonPath("$.custLastName", is(customerMaster.getCustLastName())))
		.andExpect(jsonPath("$.custEmail",is(customerMaster.getCustEmail())))
		.andExpect(jsonPath("$.custMobile", is(customerMaster.getCustMobile())))
		.andExpect(jsonPath("$.custPassword",is(customerMaster.getCustPassword())));


	}

	@Test
	void testGetAllCustomer() throws Exception{
		CustomerMaster customerMaster=new CustomerMaster();
		customerMaster.setCustId(custId);
		customerMaster.setCustFirstName(firstName);
		customerMaster.setCustLastName(LastName);
		customerMaster.setCustEmail(email);
		customerMaster.setCustMobile(mobile);
		customerMaster.setCustPassword(password);
		List<CustomerMaster> customerList=new ArrayList<CustomerMaster>();
		customerList.add(customerMaster);
		Mockito.when(customerService.getAllCustomer()).thenReturn(customerList);

		// Execute the GET request
		mockMvc.perform(get("/api/customers/"))

		// Validate the response code 
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].custId",is(customerMaster.getCustId())))
		.andExpect(jsonPath("$[0].custFirstName", is(customerMaster.getCustFirstName())))
		.andExpect(jsonPath("$[0].custLastName", is(customerMaster.getCustLastName())))
		.andExpect(jsonPath("$[0].custEmail",is(customerMaster.getCustEmail())))
		.andExpect(jsonPath("$[0].custMobile", is(customerMaster.getCustMobile())))
		.andExpect(jsonPath("$[0].custPassword",is(customerMaster.getCustPassword())));

	}

	@Test
	void testAddCustomer() throws Exception {
		CustomerMaster customerMaster=new CustomerMaster();
		customerMaster.setCustId(custId);
		customerMaster.setCustFirstName(firstName);
		customerMaster.setCustLastName(LastName);
		customerMaster.setCustEmail(email);
		customerMaster.setCustMobile(mobile);
		customerMaster.setCustPassword(password);
		Mockito.when(customerService.addCustomer(customerMaster)).thenReturn(1);
		String json = mapper.writeValueAsString(customerMaster);

		// Execute the POST request
		MvcResult mvcResult=mockMvc.perform(post("/api/customers/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")
				.content(json))
				.andReturn();

		// Validate the response code 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void testDeleteCustomer() throws Exception {	
		Mockito.when(customerService.deleteCustomer(1)).thenReturn(1);

		// Execute the DELETE request
		MvcResult mvcResult=mockMvc.perform(delete("/api/customers/1")
				.param("custId", "1"))
				.andReturn();

		// Validate the response code
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	void testUpdateCustomer() throws Exception {
		CustomerMaster customerMaster=new CustomerMaster();
		customerMaster.setCustId(custId);
		customerMaster.setCustFirstName("Rohit");
		Mockito.when(customerService.updateCustomer(customerMaster)).thenReturn(customerMaster);
		String json = mapper.writeValueAsString(customerMaster);

		//Execute put request
		mockMvc.perform(put("/api/customers/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")
				.content(json))
		
		// Validate the response code
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$.custId",is(customerMaster.getCustId())))
		.andExpect(jsonPath("$.custFirstName", is(customerMaster.getCustFirstName())));

	}

}
