package com.capgemini.advertisement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.advertisement.entity.LogOutPayload;
import com.capgemini.advertisement.entity.Login;
import com.capgemini.advertisement.exception.BaseResponse;
import com.capgemini.advertisement.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Sandhya and Shweta
 *
 */

@RestController
//Maps a specific request path or pattern onto a controller
@RequestMapping("/api/customerLogin")
@Api(value = "CustomerMaster")
public class LoginController
{
	@Autowired 
	private LoginService loginService;

	//sign in the customer
	@PostMapping("/custlogin") 
	@ApiOperation(value = "SignIn")
	public ResponseEntity<?> signIn( @RequestBody Login customerMaster) 
	{
		String str = loginService.signIn(customerMaster);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	//signing out the customer
	@PostMapping("/custlogout") 
	@ApiOperation(value = "SignOut")
	public ResponseEntity<?> signOut( @RequestBody LogOutPayload customerMaster) 
	{
		String str = loginService.signOut(customerMaster);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	//Resetting the password of customer
	@PostMapping("/custreset")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody Login customerMaster, String newPassword) 
	{
		String str =loginService.changePassword(customerMaster, newPassword);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
}