package com.capgemini.advertisement.service;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;



import com.capgemini.advertisement.dao.LoginRepository;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.ForgotPassword;
import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.Login;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.entity.StaffLogin;
import com.capgemini.advertisement.exception.OperationFailedException;
import com.capgemini.advertisement.exception.ResourceNotFound;
import com.capgemini.advertisement.exception.ResourceNotFoundException;
import static com.capgemini.advertisement.exception.AppConstants.OPERATION_FAILED;
import static com.capgemini.advertisement.exception.AppConstants.USER_NOT_FOUND;
import static com.capgemini.advertisement.exception.AppConstants.WRONG_PASSWORD;
import java.util.Optional;


/**
 * 
 * @author Sandhya and Shweta
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired // To get a relation with User repository
	private LoginRepository loginRepository;

	/**
	 * signIn
	 */
	@Override
	public String signIn(Login customerMaster) {
		String str=null;
		Optional<CustomerMaster> customerObj=loginRepository.findByEmailId(customerMaster.getEmail());
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

	/**
	 * signOut
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

	/**
	 * changePassword
	 */
	@Override
	public String changePassword(Login customerMaster, String new_password) {
		String str = null;
		Optional<CustomerMaster> customerObj = loginRepository.findByEmailId(customerMaster.getEmail());
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
	
	@Override
	public String forgotPassword(ForgotPassword customerMaster, String newPassword) {
		String str = null;
		Optional<CustomerMaster> userObj = loginRepository.findByEmailId(customerMaster.getEmail());
		
		if (!userObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String lastName = userObj.get().getCustLastName();
			if (!lastName.equals(customerMaster.getlastName())) {
				throw new ResourceNotFound(USER_NOT_FOUND);
			}
			try {
				userObj.get().setCustPassword(newPassword);
				loginRepository.saveAndFlush(userObj.get());
				str = "Password updated sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}

}