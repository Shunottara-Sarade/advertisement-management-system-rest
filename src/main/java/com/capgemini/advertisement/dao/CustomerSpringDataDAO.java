package com.capgemini.advertisement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.CustomerMaster;



//Spring Data JPA

@Repository
public interface CustomerSpringDataDAO
				extends JpaRepository<CustomerMaster,Integer>{
	
}

