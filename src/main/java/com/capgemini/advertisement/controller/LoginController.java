package com.capgemini.advertisement.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.advertisement.entity.ForgotPassword;
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
@RequestMapping("/api/customerLogin")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "CustomerMaster")
public class LoginController 
{
    @Autowired 
    private LoginService loginService;

 

    /**
     * @param customerMaster
     * Login of customer
     * @return
     */
    @PostMapping("/login") 
    @ApiOperation(value = "SignIn")
    public ResponseEntity<?> signIn( @RequestBody Login customerMaster) {
        String str = loginService.signIn(customerMaster);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

 

    /**
     * @param customerMaster
     * Logout of customer
     * @return
     */
    @PostMapping("/logout") 
    @ApiOperation(value = "SignOut")
    public ResponseEntity<?> signOut( @RequestBody LogOutPayload customerMaster) {
        String str = loginService.signOut(customerMaster);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

 

    /**
     * @param customerMaster
     * @param new_password
     * Reset password of customer
     * @return
     */
    @PostMapping("/reset/{newPassword}")
    @ApiOperation(value = "Reset Password")
    public ResponseEntity<?> changePassword( @RequestBody Login customerMaster,@PathVariable String newPassword) {
        String str =loginService.changePassword(customerMaster, newPassword);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    
    
    @PostMapping("/forgot/{newPassword}")
	@ApiOperation(value = "Forgot Password")
	public ResponseEntity<?> forgotPassword( @RequestBody ForgotPassword customerMaster,@PathVariable String newPassword) {
		String str =loginService.forgotPassword(customerMaster, newPassword);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
}