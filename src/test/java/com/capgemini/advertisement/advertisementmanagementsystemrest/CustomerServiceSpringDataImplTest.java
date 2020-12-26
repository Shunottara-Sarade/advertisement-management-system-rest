package com.capgemini.advertisement.advertisementmanagementsystemrest;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.advertisement.dao.CustomerSpringDataDAO;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;
import com.capgemini.advertisement.service.CustomerServiceSpringDataImpl;
import static org.mockito.Mockito.verify;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceSpringDataImplTest {
	@Mock
	private CustomerSpringDataDAO customerSpringDataDao;
	
	@Mock
	private CustomerMaster customerMaster;
	
	@InjectMocks
	private CustomerServiceSpringDataImpl customerService;
	

	@Test
	void testAddCustomer() throws CustomerException {
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		
		given(customerSpringDataDao.save(customerMaster)).willReturn(customerMaster);
		Integer savedCustomer=customerService.addCustomer(customerMaster);
		Assertions.assertThat(savedCustomer).isNotNull();
		verify(customerSpringDataDao).save(any(CustomerMaster.class));
	}

	@Test
	void testGetCustomerById() throws CustomerException {
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		given(customerSpringDataDao.findById(1)).willReturn(Optional.of(customerMaster));
		CustomerMaster getCustomerId= customerService.getCustomerById(1);
		Assertions.assertThat(getCustomerId).isNotNull();
		
	}

	@Test
	void testDeleteCustomer() throws CustomerException {
		Integer customerId=1;
		customerService.deleteCustomer(customerId);
		customerService.deleteCustomer(customerId);
		verify(customerSpringDataDao,times(2)).deleteById(customerId);
	}

	@Test
	void testGetAllCustomer() throws CustomerException {
		List<CustomerMaster> customerList=new ArrayList<CustomerMaster>();
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		customerList.add(customerMaster);
		 given(customerSpringDataDao.findAll()).willReturn(customerList);
		List<CustomerMaster> custList=customerService.getAllCustomer();
		Assertions.assertThat(custList).isEqualTo(customerList);
	}

	@Test
	void testUpdateCustomer() throws CustomerException {
		customerMaster.setCustId(1);
		customerMaster.setCustFirstName("Virat");
		customerMaster.setCustLastName("Kohli");
		customerMaster.setCustEmail("virat@gmail.com");
		customerMaster.setCustMobile("8798451232");
		customerMaster.setCustPassword("virat@123");
		given(customerSpringDataDao.save(customerMaster)).willReturn(customerMaster);
		CustomerMaster expectedCustomer=customerService.updateCustomer(customerMaster);
Assertions.assertThat(expectedCustomer).isNotNull();
	    
	    verify(customerSpringDataDao).save(any(CustomerMaster.class));
	}

}
