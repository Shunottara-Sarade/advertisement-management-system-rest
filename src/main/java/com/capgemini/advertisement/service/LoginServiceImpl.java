package com.capgemini.advertisement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.advertisement.dao.LoginRepository;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.Login;
import com.capgemini.advertisement.exception.OperationFailedException;
import com.capgemini.advertisement.exception.ResourceNotFound;
import static com.capgemini.advertisement.exception.AppConstants.OPERATION_FAILED;
import static com.capgemini.advertisement.exception.AppConstants.USER_NOT_FOUND;
import static com.capgemini.advertisement.exception.AppConstants.WRONG_PASSWORD;
import java.util.Optional;
/**
 * 
 * @author Sandhya and Shweta
 *
 */

//@Service annotation is used to mark the class as a service provider
@Service
public class LoginServiceImpl implements LoginService {

	// LoginServiceimpl should implement all the methods present in LoginService
	// interface

	@Autowired // To get a relation with User repository
	private LoginRepository loginRepository;

	// ****************************************//
	/*
	 * signin the page by Staff using StaffId and the password
	 */
	@Override
	public String signIn(Login customerMaster) {
		String str = null;
		Optional<CustomerMaster> customerObj = loginRepository.findById(customerMaster.getCustId());
		if (!customerObj.isPresent()) {
			System.out.println(customerObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = customerObj.get().getCustPassword();
			if (!pwd.equals(customerMaster.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				str = "Sign in sucessfull";
				loginRepository.saveAndFlush(customerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}

	// ****************************************//
	/*
	 * signout the page by Staff using StaffId and the password
	 */
	@Override
	public String signOut(LogOutPayload customerMaster) {
		String str = null;
		Optional<CustomerMaster> customerObj = loginRepository.findById(1);
		if (!customerObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			try {
				str = "Sign Out sucessfull";
				loginRepository.saveAndFlush(customerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}


	// ****************************************//
	/*
	 * retest password
	 */
	@Override
	public String changePassword(Login customerMaster, String new_password) {
		String str = null;
		Optional<CustomerMaster> customerObj = loginRepository.findById(customerMaster.getCustId());
		if (!customerObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = customerObj.get().getCustPassword();
			if (!pwd.equals(customerMaster.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				customerObj.get().setCustPassword(new_password);
				loginRepository.saveAndFlush(customerObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}
}