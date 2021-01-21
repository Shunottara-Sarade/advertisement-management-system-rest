package com.capgemini.advertisement.advertisementmanagementsystemrest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.capgemini.advertisement.dao.AdvertisementDetailSpringDataDAO;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.Role;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.AdvertisementException;
import com.capgemini.advertisement.exception.CustomerException;
import com.capgemini.advertisement.exception.StaffException;
import com.capgemini.advertisement.service.AdvertisementServiceSpringDataImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdvertisementServiceDataImplTest {

	@Mock
	private AdvertisementDetailSpringDataDAO advertisementSpringDataDAO;
	@Mock
	private CustomerMaster customerMaster;
	
	@Mock 
	private AdvertisementDetails advertisement;
	
	@Mock
	private Staff staff;

	@InjectMocks
	private AdvertisementServiceSpringDataImpl advertisementService;
	
	

	//testing deleteAdvertisement method of Advertisement
	@Test
	void testDeleteAdvertisement() throws AdvertisementException 
	{		
		Integer Id=1;
		advertisementService.deleteAdvertisement(Id);
		verify(advertisementSpringDataDAO,times(1)).deleteById(Id);
	}

	//testing getAllAdvertisement method of Advertisement
	@Test
	void testGetAllAdvertisement() throws AdvertisementException 
	{
		List<AdvertisementDetails> advertisementList = new ArrayList<AdvertisementDetails>();
		advertisement.setId(1);
		advertisement.setAdvType("commercial");
		advertisement.setCreatedBy("Own");
		advertisement.setAdvLocation("Pune");
		advertisement.setStartDate(LocalDate.of(2020, 11, 11));
		advertisement.setEndDate(LocalDate.of(2021, 11, 11));
	
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");

		advertisementList.add(advertisement);
		given(advertisementSpringDataDAO.findAll()).willReturn(advertisementList);
		List<AdvertisementDetails> advertisementList1=advertisementService.getAllAdvertisement();
		Assertions.assertThat(advertisementList1).isEqualTo(advertisementList);
	}

	//testing getAdvertisementById method of Advertisement
	@Test
	void testGetAdvertisementById() throws AdvertisementException 
	{
		advertisement.setId(1);
		advertisement.setAdvType("commercial");
		advertisement.setCreatedBy("Own");
		advertisement.setAdvLocation("Pune");
		advertisement.setStartDate(LocalDate.of(2020, 11, 11));
		advertisement.setEndDate(LocalDate.of(2021, 11, 11));
		
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");

		given(advertisementSpringDataDAO.findById(1)).willReturn(Optional.of(advertisement));
		AdvertisementDetails getAdvertisementId= advertisementService.getAdvertisementById(1);
		Assertions.assertThat(getAdvertisementId).isNotNull();
	}

	//testing updateAdvertisement method of Advertisement
	@Test
	public void updateCustomerTest() throws AdvertisementException 
	{
		advertisement.setId(1);
		advertisement.setAdvType("commercial");
		advertisement.setCreatedBy("Own");
		advertisement.setAdvLocation("Pune");
		advertisement.setStartDate(LocalDate.of(2020, 11, 11));
		advertisement.setEndDate(LocalDate.of(2021, 11, 11));
	
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		
		staff.setStaffId(1);
		staff.setFirstName("Shweta");
		staff.setLastName("Nangare");
		staff.setEmail("shweta@gmail.com");
		staff.setMobileNo("9876543212");
		staff.setRole(Role.ADMIN);
		staff.setPassword("Shweta@123");
		given(advertisementSpringDataDAO.save(advertisement)).willReturn(advertisement);
		//AdvertisementDetails expectedAdvertisement=advertisementService.updateAdvertisement(advertisement);
		//Assertions.assertThat(expectedAdvertisement).isNotNull();
	    
	    verify(advertisementSpringDataDAO).save(any(AdvertisementDetails.class));
	}


}
