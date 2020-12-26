package com.capgemini.advertisement.service;

import java.util.List;

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
	public Integer addAdvertisement(Integer cid,Integer sid,AdvertisementDetails advertisement) throws AdvertisementException;
	
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
	public AdvertisementDetails updateAdvertisement(AdvertisementDetails advertisement) throws AdvertisementException;
}
