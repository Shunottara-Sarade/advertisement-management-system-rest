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
import com.capgemini.advertisement.entity.Role;
import com.capgemini.advertisement.entity.Staff;

import com.capgemini.advertisement.service.StaffServiceImpl;
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
@ContextConfiguration(classes = {StaffController.class})
@WebAppConfiguration
class StaffControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StaffServiceImpl staffService;

	private static ObjectMapper mapper = new ObjectMapper();
	private final Integer staffId=1;
	private final String firstName="Virat";
	private final String lastName="Kohli";
	private final String email="virat@gmail.com";
	private final String mobile="9852123265";
	private final String password="virat@123";
	private Role role;
	
	
	@Test
	void testGetStaffById() throws Exception {
		Staff staff=new Staff();
		staff.setStaffId( staffId);
		staff.setFirstName(firstName);
		staff.setLastName(lastName);
		staff.setEmail(email);
		staff.setMobileNo(mobile);
		staff.setRole(role.ADMIN);
		staff.setPassword(password);
		Mockito.when(staffService.getStaffById(staffId)).thenReturn(staff);

		// Execute the GET request
		mockMvc.perform(get("/api/staff/1"))

		// Validate the response code
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$.staffId",is(staff.getStaffId())))
		.andExpect(jsonPath("$.firstName", is(staff.getFirstName())))
		.andExpect(jsonPath("$.lastName", is(staff.getLastName())))
		.andExpect(jsonPath("$.email",is(staff.getEmail())))
		.andExpect(jsonPath("$.mobileNo", is(staff.getMobileNo())))
		.andExpect(jsonPath("$.role",is(staff.getRole().toString())))
		.andExpect(jsonPath("$.password",is(staff.getPassword())));
	}

	@Test
	void testGetAllStaff() throws Exception{
		Staff staff=new Staff();
		staff.setStaffId(staffId);
		staff.setFirstName(firstName);
		staff.setLastName(lastName);
		staff.setEmail(email);
		staff.setMobileNo(mobile);
		staff.setRole(Role.ADMIN);
		staff.setPassword(password);
		List<Staff> staffList=new ArrayList<Staff>();
		staffList.add(staff);
		Mockito.when(staffService.getAllStaff()).thenReturn(staffList);

		// Execute the GET request
		mockMvc.perform(get("/api/staff/"))

		// Validate the response code 
		.andExpect(status().isOk())

		// Validate the returned fields
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].staffId",is(staff.getStaffId())))
		.andExpect(jsonPath("$[0].firstName", is(staff.getFirstName())))
		.andExpect(jsonPath("$[0].lastName", is(staff.getLastName())))
		.andExpect(jsonPath("$[0].email",is(staff.getEmail())))
		.andExpect(jsonPath("$[0].mobileNo", is(staff.getMobileNo())))
		.andExpect(jsonPath("$[0].role",is(staff.getRole().toString())))
		.andExpect(jsonPath("$[0].password",is(staff.getPassword())));
	}

	@Test
	void testAddStaff() throws Exception {
		Staff staff=new Staff();
		staff.setStaffId(staffId);
		staff.setFirstName(firstName);
		staff.setLastName(lastName);
		staff.setEmail(email);
		staff.setRole(role.ADMIN);
		staff.setMobileNo(mobile);
		staff.setPassword(password);
		Mockito.when(staffService.addStaff(staff)).thenReturn(1);
		String json = mapper.writeValueAsString(staff);

		// Execute the POST request
		MvcResult mvcResult=mockMvc.perform(post("/api/staff/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")
				.content(json))
				.andReturn();

		// Validate the response code 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void testDeleteStaff() throws Exception {
		Mockito.when(staffService.deleteStaff(1)).thenReturn(1);

		// Execute the DELETE request
		MvcResult mvcResult=mockMvc.perform(delete("/api/staff/1")
				.param("staffId", "1"))
				.andExpect(status().isOk())
				.andReturn();

		// Validate the response code 
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	void testUpdateStaff() throws Exception {
		Staff staff=new Staff();
		staff.setStaffId(staffId);
		staff.setMobileNo("9851451232");
		Mockito.when(staffService.updateStaff(staff)).thenReturn(staff);
		String json = mapper.writeValueAsString(staff);

		//Execute put request
		mockMvc.perform(put("/api/staff/")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")
				.content(json))
		// Validate the response code
		.andExpect(status().isOk())
		
		// Validate the returned fields
		.andExpect(jsonPath("$.staffId",is(staff.getStaffId())))
		.andExpect(jsonPath("$.firstName", is(staff.getFirstName())));
	}

}