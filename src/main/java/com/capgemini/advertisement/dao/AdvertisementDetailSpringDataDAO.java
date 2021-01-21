package com.capgemini.advertisement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.entity.CustomerMaster;

/**
 * 
 * @author Dakshata and Sapna
 *
 */
@Repository
public interface AdvertisementDetailSpringDataDAO extends JpaRepository<AdvertisementDetails, Integer>{
	@Query("From AdvertisementDetails ad where ad.customer=?1")
    List<AdvertisementDetails> findByCustomer(CustomerMaster customer);
	
	@Query("Select ad from AdvertisementDetails ad where ad.advType=?1 and ad.isPosted=1")
    List<AdvertisementDetails> findImagesByType(String advType);
}
