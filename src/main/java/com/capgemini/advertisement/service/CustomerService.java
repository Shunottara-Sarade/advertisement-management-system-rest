package com.capgemini.advertisement.service;
import java.util.List;

import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;

public interface CustomerService {
	public Integer addCustomer(CustomerMaster customer) throws CustomerException;
	public CustomerMaster getCustomerById(Integer cust_id) throws CustomerException;
	public Integer deleteCustomer(Integer cust_id) throws CustomerException;
	public List<CustomerMaster> getAllCustomer() throws CustomerException;
	public CustomerMaster updateCustomer(CustomerMaster customer) throws CustomerException;
}