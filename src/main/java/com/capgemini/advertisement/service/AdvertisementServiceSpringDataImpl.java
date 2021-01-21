package com.capgemini.advertisement.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.advertisement.dao.AdvertisementDetailSpringDataDAO;
import com.capgemini.advertisement.dao.CustomerSpringDataDAO;
import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.AddAdvertisement;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.AdvertisementException;
import com.capgemini.advertisement.exception.CustomerException;

/**
 * 
 * @author Sapna and Dakshata
 *
 */

@Service
@Transactional
public class AdvertisementServiceSpringDataImpl implements AdvertisementService{
	@Autowired
	private AdvertisementDetailSpringDataDAO advertisementDetailSpringDataDaoImpl;

	@Autowired
	private CustomerSpringDataDAO customerSpringDataDao;

	@Autowired
	private StaffSpringDataDAO staffSpringDataDao;

	/**
	 *add advertisement
	 */

	@Override
	public AdvertisementDetails addAdvertisement(AddAdvertisement advertisement) throws AdvertisementException {
		AdvertisementDetails advertisementDetails=new AdvertisementDetails();
		Integer custId=advertisement.getCustId();
		Integer staffId=advertisement.getStaffId();
		try
		{
			advertisementDetails.setAdvLocation(advertisement.getAdvLocation());
			advertisementDetails.setAdvType(advertisement.getAdvType());
			advertisementDetails.setCreatedBy(advertisement.getCreatedBy());
			advertisementDetails.setStartDate(advertisement.getStartDate());
			advertisementDetails.setEndDate(advertisement.getEndDate());
			advertisementDetails.setIsPosted(0);
			if(advertisement.getAdvName()==null) {
				advertisementDetails.setImageStatus("Image Not Uploaded");
			}

			if(custId != null)
			{
				Optional<CustomerMaster> optional=customerSpringDataDao.findById(custId);
				if(optional.isPresent())
				{
					advertisementDetails.setCustomer(optional.get());
				}
			}



			if(staffId != null)
			{
				Optional<Staff> staff=staffSpringDataDao.findById(staffId);
				if(staff.isPresent())
				{
					advertisementDetails.setStaff(staff.get());
				}
			}



			advertisementDetailSpringDataDaoImpl.save(advertisementDetails);
			return advertisementDetails;
		}
		catch(DataAccessException dataAccessException)
		{
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}


	/**
	 * getAdvertisementById
	 */
	@Override
	public AdvertisementDetails getAdvertisementById(Integer id) throws AdvertisementException {
		try {
			Optional<AdvertisementDetails> optional= 
					advertisementDetailSpringDataDaoImpl.findById(id);
			if(optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			}else {
				return null;
			}			
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}	
	}

	/**
	 * deleteAdvertisement
	 */
	@Override
	public Integer deleteAdvertisement(Integer id) throws AdvertisementException {
		try {
			advertisementDetailSpringDataDaoImpl.deleteById(id);
			return 1;
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	/**
	 * getAllAdvertisement
	 */
	@Override
	public List<AdvertisementDetails> getAllAdvertisement() throws AdvertisementException {
		try {
			List<AdvertisementDetails> adList=
					advertisementDetailSpringDataDaoImpl.findAll();
			return adList;
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	/**
	 * updateAdvertisement
	 */
	@Override
	public AdvertisementDetails updateAdvertisement(AddAdvertisement advertisement) throws AdvertisementException {

		Integer id=advertisement.getId();
		AdvertisementDetails advertisementDetails=advertisementDetailSpringDataDaoImpl.findById(id).get();
		Integer custId=advertisement.getCustId();
		Integer staffId=advertisement.getStaffId();
		String advLocation=advertisement.getAdvLocation();
		String advType=advertisement.getAdvType();
		String createdBy=advertisement.getCreatedBy();
		LocalDate startDate=advertisement.getStartDate();
		LocalDate endDate=advertisement.getEndDate();
		String imageUri=advertisement.getLink();
		Integer isPosted=advertisement.getIsPosted();
		try
		{
			if(advLocation != null)
			{
				advertisementDetails.setAdvLocation(advLocation);
			}
			if(advType != null)
			{
				advertisementDetails.setAdvType(advType);
			}
			if(createdBy != null)
			{
				advertisementDetails.setCreatedBy(createdBy);
			}
			if(startDate != null)
			{
				advertisementDetails.setStartDate(startDate);
			}
			if(endDate != null)
			{
				advertisementDetails.setEndDate(endDate);
			}



			if(custId != null)
			{
				Optional<CustomerMaster> optional=customerSpringDataDao.findById(custId);
				if(optional.isPresent())
				{
					advertisementDetails.setCustomer(optional.get());
				}
			}
			if(imageUri != null)
			{
				advertisementDetails.setLink(imageUri);
			}



			if(staffId != null)
			{
				Optional<Staff> staff=staffSpringDataDao.findById(staffId);
				if(staff.isPresent())
				{
					advertisementDetails.setStaff(staff.get());
				}
			}
			if(isPosted != null)
			{
				advertisementDetails.setIsPosted(isPosted);
			}


			advertisementDetailSpringDataDaoImpl.save(advertisementDetails);
			return advertisementDetails;
		}

		catch(DataAccessException dataAccessException)
		{
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	@Override
	public boolean uploadAdvertisementImage(MultipartFile file, Integer id) throws AdvertisementException {
		boolean isUploaded=false;
		try {
			AdvertisementDetails advertisement=advertisementDetailSpringDataDaoImpl.findById(id).get();
			String fileName=StringUtils.cleanPath(file.getOriginalFilename());
			advertisement.setAdvImage(file.getBytes());
			advertisement.setAdvName(fileName);

			advertisement.setImageStatus("Image Uploaded");

			advertisementDetailSpringDataDaoImpl.save(advertisement);

			//advertisementDetailSpringDataDaoImpl.save(advertisement);

			isUploaded = true;

		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch (IOException e) {
			throw new AdvertisementException(e.getMessage(), e);
		} catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
		return isUploaded;

	}


	@Override
	public String getAdvertisementNameById(Integer id) throws AdvertisementException {
		try {
			AdvertisementDetails advertisement=advertisementDetailSpringDataDaoImpl.findById(id).get();
			String fileName=advertisement.getAdvName();
			return fileName;
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}


	@Override
	public byte[] getAdveertisementsById(Integer id) throws AdvertisementException {
		try {
			AdvertisementDetails advertisement = advertisementDetailSpringDataDaoImpl.findById(id).get();
			return advertisement.getAdvImage();
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	@Override
	public Integer getIdByEmail(String email) throws AdvertisementException
	{
		try
		{
			Optional<CustomerMaster> customer= customerSpringDataDao.findByEmailId(email);
			if(customer.isPresent())
			{
				return customer.get().getCustId();
			}
			else
			{
				throw new AdvertisementException("ID not found");
			}
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	@Override
	public List<AdvertisementDetails> findByCustomer(String email) throws AdvertisementException 
	{
		List<AdvertisementDetails> list=null;
		try
		{
			Optional<CustomerMaster> customer=customerSpringDataDao.findByEmailId(email);
			if(customer.isPresent())
			{
				list = advertisementDetailSpringDataDaoImpl.findByCustomer(customer.get());
				if(list.size()==0)
				{
					throw new AdvertisementException("No advertisement found");
				}
				return list;
			}
			else
			{
				throw new AdvertisementException();
			}

		}
		catch(DataAccessException dataAccessException)
		{        
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

	@Override
	public List<AdvertisementDetails> getImagesByType(String advType) throws AdvertisementException
	{
		try
		{
			List<AdvertisementDetails> images=advertisementDetailSpringDataDaoImpl.findImagesByType(advType);
			if(images.size()!=0)
			{
				return images;
			}
			else
			{
				throw new AdvertisementException("Images not found of specific category");
			}
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

}
