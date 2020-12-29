package com.capgemini.advertisement.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.capgemini.advertisement.controller.AdvertisementController;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.Role;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.service.AdvertisementServiceSpringDataImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@WebMvcTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdvertisementController.class})
@WebAppConfiguration
class AdvertisementControllerTest 
{	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdvertisementServiceSpringDataImpl advertisementService;

	private static ObjectMapper mapper = new ObjectMapper();


	@Test
	void testGetAdvertisementById() throws Exception {
		AdvertisementDetails advertisement= new AdvertisementDetails();

		advertisement.setId(1);
		advertisement.setAdvType("commercial");
		advertisement.setCreatedBy("Own");
		advertisement.setAdvLocation("Pune");
		advertisement.setStartDate(LocalDate.of(2020, 11, 11));
		advertisement.setEndDate(LocalDate.of(2021, 11, 11));		
		Mockito.when(advertisementService.getAdvertisementById(1)).thenReturn(advertisement);

		// Execute the GET request
		mockMvc.perform(get("/api/advertisement/1"))

		// Validate the response code
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$.id",is(1)))
		.andExpect(jsonPath("$.advType", is("commercial")))
		.andExpect(jsonPath("$.createdBy", is("Own")))
		.andExpect(jsonPath("$.advLocation",is("Pune")))
		.andExpect(jsonPath("$.startDate", is("2020-11-11")))
		.andExpect(jsonPath("$.endDate",is("2021-11-11")));
	}

	@Test
	void testGetAllAdvertisement() throws Exception{
		AdvertisementDetails advertisement= new AdvertisementDetails();

		advertisement.setId(1);
		advertisement.setAdvType("commercial");
		advertisement.setCreatedBy("Own");
		advertisement.setAdvLocation("Pune");
		advertisement.setStartDate(LocalDate.of(2020, 11, 11));
		advertisement.setEndDate(LocalDate.of(2021, 11, 11));

		List<AdvertisementDetails> advertisementList=new ArrayList<AdvertisementDetails>();
		advertisementList.add(advertisement);
		Mockito.when(advertisementService.getAllAdvertisement()).thenReturn(advertisementList);

		// Execute the GET request
		mockMvc.perform(get("/api/advertisement/"))

		// Validate the response code
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id",is(1)))
		.andExpect(jsonPath("$[0].advType", is("commercial")))
		.andExpect(jsonPath("$[0].createdBy", is("Own")))
		.andExpect(jsonPath("$[0].advLocation",is("Pune")))
		.andExpect(jsonPath("$[0].startDate", is("2020-11-11")))
		.andExpect(jsonPath("$[0].endDate",is("2021-11-11")));
	}



	@Test
	void testDeleteAdvertisement() throws Exception {
		Mockito.when(advertisementService.deleteAdvertisement(1)).thenReturn(1);

		//Execute DELETE Request
		MvcResult mvcResult=mockMvc.perform(delete("/api/advertisement/1")
				.param("id", "1"))
				.andReturn();

		// Validate the response code
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void testUpdateAdvertisement() throws Exception {
		AdvertisementDetails advertisement= new AdvertisementDetails();

		advertisement.setId(1);
		advertisement.setAdvType("Clothes");
		Mockito.when(advertisementService.updateAdvertisement(advertisement)).thenReturn(advertisement);
		String json = mapper.writeValueAsString(advertisement);

		//Execute PUT Request
		mockMvc.perform(put("/api/advertisement/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json))
		// Validate the response code
		.andExpect(status().isOk())
		// Validate the returned fields
		.andExpect(jsonPath("$.id",is(1)))
		.andExpect(jsonPath("$.advType", is("Clothes")));

	}


}