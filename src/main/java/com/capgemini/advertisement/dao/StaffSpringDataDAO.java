package com.capgemini.advertisement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.Staff;

/**
 * 
 * @author Shunottara and Samidha
 *
 */
@Repository
public interface StaffSpringDataDAO extends JpaRepository<Staff, Integer> {

 

}