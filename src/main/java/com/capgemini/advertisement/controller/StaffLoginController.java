package com.capgemini.advertisement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.StaffLogin;
import com.capgemini.advertisement.exception.BaseResponse;
import com.capgemini.advertisement.service.StaffLoginService;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Sandhya and Shunottara
 *
 */
@RestController
@RequestMapping("/api/staffLogin")
@Api(value = "Staff")
public class StaffLoginController {



	@Autowired 
	private StaffLoginService staffLoginService;




	@PostMapping("/login")
	@ApiOperation(value = "SignIn")
	public ResponseEntity<?> signIn( @RequestBody StaffLogin staff) {
		String str = staffLoginService.signIn(staff);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}



	@PostMapping("/logout") 
	@ApiOperation(value = "SignOut")
	public ResponseEntity<?> signOut( @RequestBody LogOutPayload staff) {
		String str = staffLoginService.signOut(staff);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}



	@PostMapping("/reset")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody StaffLogin staff, String new_password) {
		String str =staffLoginService.changePassword(staff, new_password);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}



}
