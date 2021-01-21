package com.capgemini.advertisement.service;

import java.util.List;
import java.util.Optional;
import static com.capgemini.advertisement.exception.AppConstants.Email_Exist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.advertisement.dao.CustomerSpringDataDAO;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.exception.CustomerException;
import com.capgemini.advertisement.exception.EmailAlreadyExistException;

/**
 * 
 * @author Shweta and Ashwini
 *
 */

@Service
@Transactional
public class CustomerServiceSpringDataImpl implements CustomerService{
	@Autowired
	private CustomerSpringDataDAO customerSpringDataDaoImpl;
	
	/**
	 * addCustomer
	 */
	@Override
	public Integer addCustomer(CustomerMaster customer) throws CustomerException {
		try {
			
			Optional<CustomerMaster> customerEx=customerSpringDataDaoImpl.findByEmailId(customer.getCustEmail());
            if(!customerEx.isPresent())
            {
                customerSpringDataDaoImpl.save(customer);
                return 1;
            }
            else
            {
                throw new EmailAlreadyExistException(Email_Exist);
            }
			
			
		}catch(DataAccessException dataAccessException) {
			throw new CustomerException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new CustomerException(exception.getMessage(),exception);
		}
	}

	/**
	 * getCustomerById
	 */
	@Override
	public CustomerMaster getCustomerById(Integer customerId) throws CustomerException {
		try {
			Optional<CustomerMaster> optional= 
					customerSpringDataDaoImpl.findById(customerId);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException dataAccessException) {
			throw new CustomerException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new CustomerException(exception.getMessage(),exception);
		}
	}

	/**
	 * deleteCustomer
	 */
	@Override
	public Integer deleteCustomer(Integer customerId) throws CustomerException {
		try {
			customerSpringDataDaoImpl.deleteById(customerId);
			return 1;
		}catch(DataAccessException dataAccessException) {
			throw new CustomerException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new CustomerException(exception.getMessage(),exception);
		}
	}

	/**
	 *  getAllCustomer
	 */
	@Override
	public List<CustomerMaster> getAllCustomer() throws CustomerException {
		try {
			List<CustomerMaster> customerList=
					customerSpringDataDaoImpl.findAll();
			return customerList;
		}catch(DataAccessException dataAccessException) {
			throw new CustomerException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new CustomerException(exception.getMessage(),exception);
		}
	}

	/**
	 * updateCustomer
	 */
	@Override
	public CustomerMaster updateCustomer(CustomerMaster customer) throws CustomerException {
		try {
			CustomerMaster c= 
					customerSpringDataDaoImpl.save(customer);
			return c;
		}catch(DataAccessException dataAccessException) {
			throw new CustomerException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new CustomerException(exception.getMessage(),exception);
		}
	}

}
