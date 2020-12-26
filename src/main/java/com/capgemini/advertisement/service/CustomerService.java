package com.capgemini.advertisement.service;
import java.util.List;

import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;

public interface CustomerService {
	/**
	 * Add Customer
	 * @param customer
	 * @return 1 if customer added
	 * else
	 * @throws CustomerException
	 */
	public Integer addCustomer(CustomerMaster customer) throws CustomerException;
	
	/**
	 * get customer by id
	 * @param cust_id
	 * @return customerMaster
	 * @throws CustomerException
	 */
	public CustomerMaster getCustomerById(Integer cust_id) throws CustomerException;
	
	/**
	 * delete customer
	 * @param cust_id
	 * @return 1 if customer deleted
	 * else
	 * @throws CustomerException
	 */
	public Integer deleteCustomer(Integer cust_id) throws CustomerException;
	
	/**
	 * get all customer
	 * @return list of all customer
	 * @throws CustomerException
	 */
	public List<CustomerMaster> getAllCustomer() throws CustomerException;
	
	/**
	 * update customer
	 * @param customer
	 * @return customerMaster
	 * @throws CustomerException
	 */
	public CustomerMaster updateCustomer(CustomerMaster customer) throws CustomerException;
}