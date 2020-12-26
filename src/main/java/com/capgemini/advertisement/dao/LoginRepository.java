package com.capgemini.advertisement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.advertisement.entity.CustomerMaster;

/**
 * 
 * @author Sandhya and Shweta
 *
 */

@Repository
public interface LoginRepository extends JpaRepository<CustomerMaster, Integer> 
{

}