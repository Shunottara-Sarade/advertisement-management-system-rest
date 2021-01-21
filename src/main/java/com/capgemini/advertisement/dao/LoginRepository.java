package com.capgemini.advertisement.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.CustomerMaster;

/**
 * 
 * @author Shweta and Sandhya
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<CustomerMaster, Integer> {
	@Query("select cm from CustomerMaster cm where cm.custEmail=?1")
	Optional<CustomerMaster> findByEmailId(String email);
}