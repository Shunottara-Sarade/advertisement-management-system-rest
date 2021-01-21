package com.capgemini.advertisement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.Staff;

/**
 * 
 * @author Shunottara and Sandhya
 *
 */
@Repository
public interface StaffLoginRepository extends JpaRepository<Staff, Integer> {
	@Query("select staff from Staff staff where staff.email=?1")
	Optional<Staff> findByEmailId(String email);

}