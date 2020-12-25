package com.capgemini.advertisement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.StaffException;
import com.capgemini.advertisement.service.StaffService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/staff")
@Slf4j
public class StaffController {
    @Autowired(required=false)
    //@Qualifier(value = "staffServiceSpringData")
    private StaffService staffService;
    
    //get staff by Id
    //http://localhost:8081/api/staff/1
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getProductById(@PathVariable Integer id){
        try {
            Staff staff= staffService.getStaffById(id);
            log.info("Product added"+ staff);
            return new ResponseEntity<>(staff,HttpStatus.OK);
        }catch(StaffException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

 

    //get all staff
    //http://localhost:8081/api/staff/
    @GetMapping("/")
    public ResponseEntity<List<Staff>> getAllStaff(){
        try {
            List<Staff> staffList = staffService.getAllStaff();
            log.info("Returning all staff details");
            return new ResponseEntity<>(staffList,HttpStatus.OK);
        }catch(StaffException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

 

    //http://localhost:8081/api/staff/
    //add staff    
    @PostMapping("/")
    public String addStaff(@RequestBody Staff staff) {
        try {
            Integer status= staffService.addStaff(staff);
            if(status ==1) {
                log.info("Staff:"+staff.getFirstName()+" added to database");
                return "Staff:"+staff.getFirstName()+" added to database";
            }else {
                log.debug("Unable to add staff");
                return "Unable to add staff to database";
            }

 

        }catch(StaffException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

 

    //http://localhost:8081/api/staff/1
    //delete staff
    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable Integer id) {
        try {
            Integer status= staffService.deleteStaff(id);
            if(status ==1) {
                log.info("Staff: "+id+" deleted from database");
                return "Staff: "+id+" deleted from database";
            }else {
                log.debug("Unable to delete Staff from database");
                return "Unable to delete Staff from database";
            }

 

        }catch(StaffException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

 

    //http://localhost:8081/api/staff/
    //update staff
    @PutMapping("/")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff) {
        try {
            Staff updatedStaff= staffService.updateStaff(staff);
            log.info("Staff: "+ staff.getStaffId()+ " updated");
            return new ResponseEntity<>(updatedStaff,HttpStatus.OK);

 

        }catch(StaffException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

 

 

}