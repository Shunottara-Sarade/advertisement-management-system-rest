package com.capgemini.advertisement.exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

 

import com.capgemini.advertisement.advertisementmanagementsystemrest.AdvertisementManagementSystemRestApplication;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.service.CustomerService;

 

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AdvertisementManagementSystemRestApplication.class)
class CustomerExceptionTest
{
    @Autowired
    CustomerService customerService;

 

    @Test
    void whenCustomerExceptionThrown_thenAssertionSucceeds()
    {
        Assertions.assertThrows(TransactionSystemException.class, ()->{
            CustomerMaster customerMaster=new CustomerMaster();
            customerMaster.setCustId(1);
            customerMaster.setCustFirstName("Shweta");
            customerMaster.setCustLastName("Nangare");
            customerMaster.setCustEmail("Shweta@gmail.com");
            customerMaster.setCustMobile("987786443");
            customerMaster.setCustPassword("Shweta@123");

 

            customerService.addCustomer(customerMaster);
        });
    }
}