package com.capgemini.advertisement.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.advertisement.entity.ForgotPassword;
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
public class StaffLoginController 
{
    @Autowired 
    private StaffLoginService staffLoginService;

 

    /**
     * @param staff
     * Login of staff
     * @return
     */

 

    @PostMapping("/login")
    @ApiOperation(value = "SignIn")
    public ResponseEntity<?> signIn( @RequestBody StaffLogin staff) {
        String str = staffLoginService.signIn(staff);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

 

    /**
     * @param staff
     * Logout of staff
     * @return
     */
    @PostMapping("/logout") 
    @ApiOperation(value = "SignOut")
    public ResponseEntity<?> signOut( @RequestBody LogOutPayload staff) {
        String str = staffLoginService.signOut(staff);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

 

    /**
     * @param staff
     * @param new_password
     * Reset password of staff
     * @return
     */
    @PostMapping("/reset/{newPassword}")
    @ApiOperation(value = "Reset Password")
    public ResponseEntity<?> changePassword( @RequestBody StaffLogin staff,@PathVariable String newPassword) {
        String str =staffLoginService.changePassword(staff, newPassword);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(1);
        baseResponse.setResponse(str);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    
    @PostMapping("/forgot/{newPassword}")
	@ApiOperation(value = "Forgot Password")
	public ResponseEntity<?> forgotPassword( @RequestBody ForgotPassword staff,@PathVariable String newPassword) {
		String str =staffLoginService.forgotPassword(staff, newPassword);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

}