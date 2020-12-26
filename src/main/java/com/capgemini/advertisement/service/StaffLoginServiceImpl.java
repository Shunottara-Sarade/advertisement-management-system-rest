package com.capgemini.advertisement.service;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;



import com.capgemini.advertisement.dao.StaffLoginRepository;
import com.capgemini.advertisement.entity.LogOutPayload;
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
 * @author Sandhya and Shunottara
 *
 */
@Service
public class StaffLoginServiceImpl implements StaffLoginService {

	@Autowired 
	private StaffLoginRepository staffLoginRepository;



	@Override
	public String signIn(StaffLogin staff) {
		String str = null;
		Optional<Staff> staffObj = staffLoginRepository.findById(staff.getStaffId());
		if (!staffObj.isPresent()) {
			System.out.println(staffObj);
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = staffObj.get().getPassword();
			if (!pwd.equals(staff.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				str = "Sign in sucessfull";
				staffLoginRepository.saveAndFlush(staffObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;



	}





	@Override
	public String signOut(LogOutPayload staff) {
		String str = null;
		Optional<Staff> staffObj = staffLoginRepository.findById(staff.getId());
		if (!staffObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			try {
				str = "Sign Out sucessfull";
				staffLoginRepository.saveAndFlush(staffObj.get());
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}




	@Override
	public String changePassword(StaffLogin staff, String new_password) {
		String str = null;
		Optional<Staff> staffObj = staffLoginRepository.findById(staff.getStaffId());
		if (!staffObj.isPresent()) {
			throw new ResourceNotFound(USER_NOT_FOUND);
		} else {
			String pwd = staffObj.get().getPassword();
			if (!pwd.equals(staff.getPassword())) {
				throw new ResourceNotFound(WRONG_PASSWORD);
			}
			try {
				staffObj.get().setPassword(new_password);
				staffLoginRepository.saveAndFlush(staffObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException(OPERATION_FAILED);
			}
		}
		return str;
	}
}