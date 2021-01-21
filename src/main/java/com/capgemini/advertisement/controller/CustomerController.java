package com.capgemini.advertisement.controller;

 

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;
import com.capgemini.advertisement.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 

/**
 * 
 * @author Shweta and Ashwini
 *
 */

 

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class CustomerController
{
    @Autowired(required = false)
    private CustomerService customerService;

 

    /**
     * @param id
     * Get customer by Id
     * http://localhost:8080/api/customers/1
     * @return
     */
    @ApiOperation(value = "Get customer by Id",response = CustomerMaster.class,
            tags="get-customer-by-id",consumes="custId",httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerMaster> getCustomerById(@PathVariable Integer id)
    {
        try 
        {
            CustomerMaster customer= customerService.getCustomerById(id);
            log.info("Customer added"+ customer);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
        catch(CustomerException customerException) 
        {
            log.error(customerException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,customerException.getMessage());
        }
    }

 

    /**
     * Get all customer
     * http://localhost:8080/api/customers/
     * @return
     */
    @ApiOperation(value = "Get all customer",response = CustomerMaster.class,
            tags="get-all-customer",httpMethod = "GET")
    @GetMapping("/")
    public ResponseEntity<List<CustomerMaster>> getAllCustomer()
    {
        try 
        {
            List<CustomerMaster> customerList = customerService.getAllCustomer();
            log.info("Returning all customer details");
            return new ResponseEntity<>(customerList,HttpStatus.OK);
        }
        catch(CustomerException customerException) 
        {
            log.error(customerException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,customerException.getMessage());
        }
    }

 

    /**
     * @param customer
     * Add Customer
     * http://localhost:8081/api/customers/
     * @return
     */
    @ApiOperation(value = "Add Customer",
            consumes = "receives Customer object as request body",
            response =String.class)
    @PostMapping("/")
    public String addCustomer(@Valid @RequestBody CustomerMaster customer) 
    {
        try 
        {
            Integer status= customerService.addCustomer(customer);
            if(status ==1)
            {
                log.info("customer:"+customer.getCustFirstName()+" added to database");
                return "customer:"+customer.getCustFirstName()+" added to database";
            }
            else 
            {
                log.debug("Unable to add customer");
                return "Unable to add customer to database";
            }
        }
        catch(CustomerException customerException)
        {
            log.error(customerException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,customerException.getMessage());
        }
    }

 

    /**
     * @param id
     * Delete Customer
     * http://localhost:8081/api/customer/1
     * @return
     */
    @ApiOperation(value = "Delete Customer",
            consumes = "custId",
            response =String.class)
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Integer id) 
    {
        try
        {
            Integer status= customerService.deleteCustomer(id);
            if(status ==1) 
            {
                log.info("customer: "+id+" deleted from database");
                return "customer: "+id+" deleted from database";
            }
            else
            {
                log.debug("Unable to delete customer from database");
                return "Unable to delete customer from database";
            }

 

        }
        catch(CustomerException customerException)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,customerException.getMessage());
        }
    }

 

    /**
     * @param customerMaster
     * Update Customer
     * http://localhost:8081/api/customer/
     * @return
     */
    @ApiOperation(value = "Update Customer",
            consumes = "receives Customer object as request body",
            response =CustomerMaster.class)
    @PutMapping("/")
    public ResponseEntity<CustomerMaster> updateCustomer(@RequestBody CustomerMaster customerMaster)
    {
        try
        {
            CustomerMaster updatedCustomer= customerService.updateCustomer(customerMaster);
            log.info("Product: "+ customerMaster.getCustId()+ " updated");
            return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
        }
        catch(CustomerException customerException) 
        {
            log.error(customerException.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,customerException.getMessage());
        }
    }
}