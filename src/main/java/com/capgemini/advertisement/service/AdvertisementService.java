package com.capgemini.advertisement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.advertisement.entity.AddAdvertisement;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.exception.AdvertisementException;

public interface AdvertisementService {
	/**
	 * add advertisement
	 * @param cid
	 * @param sid
	 * @param advertisement
	 * @return 1 if advertisement added
	 * else
	 * @throws AdvertisementException
	 */
	public AdvertisementDetails addAdvertisement(AddAdvertisement advertisement) throws AdvertisementException;
	
	/**
	 * get advertisement by id
	 * @param id
	 * @return advertisementDetails
	 * @throws AdvertisementException
	 */
	public AdvertisementDetails getAdvertisementById(Integer id) throws AdvertisementException;
	
	/**
	 * delete advertisement
	 * @param id
	 * @return 1 if advertisement deleted
	 * else
	 * @throws AdvertisementException
	 */
	
	public Integer deleteAdvertisement(Integer id) throws AdvertisementException;
	
	/**
	 * get all advertisement
	 * @return list
	 * @throws AdvertisementException
	 */
	public List<AdvertisementDetails> getAllAdvertisement() throws AdvertisementException;
	
	/**
	 * update advertisement
	 * @param advertisement
	 * @return advertisementDetails
	 * @throws AdvertisementException
	 */
	public AdvertisementDetails updateAdvertisement(AddAdvertisement advertisement) throws AdvertisementException;
	
	public boolean uploadAdvertisementImage(MultipartFile file, Integer id) throws AdvertisementException;

	public String getAdvertisementNameById(Integer id) throws AdvertisementException;
	
	public byte[] getAdveertisementsById(Integer id) throws AdvertisementException;
	
	public Integer getIdByEmail(String email) throws AdvertisementException;
	
	public List<AdvertisementDetails> findByCustomer(String email) throws AdvertisementException;

	public List<AdvertisementDetails> getImagesByType(String advType) throws AdvertisementException;
}
