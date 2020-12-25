package com.capgemini.advertisement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.advertisement.entity.AdvertisementDetails;

@Repository
public interface AdvertisementDetailSpringDataDAO extends JpaRepository<AdvertisementDetails, Integer>{
	
}
