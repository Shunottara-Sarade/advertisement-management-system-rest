package com.capgemini.advertisement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.advertisement.dao.AdvertisementDetailSpringDataDAO;
import com.capgemini.advertisement.dao.CustomerSpringDataDAO;
import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.AdvertisementDetails;
import com.capgemini.advertisement.entity.CustomerMaster;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.AdvertisementException;
import com.capgemini.advertisement.exception.CustomerException;

@Service
@Transactional
public class AdvertisementServiceSpringDataImpl implements AdvertisementService{
	@Autowired
	private AdvertisementDetailSpringDataDAO advertisementDetailSpringDataDaoImpl;
	
	@Autowired
	private CustomerSpringDataDAO customerSpringDataDao;
	
	@Autowired
	private StaffSpringDataDAO staffSpringDataDao;
	
	@Override
	public Integer addAdvertisement(Integer cid, Integer sid,AdvertisementDetails advertisement) throws AdvertisementException {
		try {
			//advertisement.setCustomer(customers.save(advertisement.getCustomer()));
			
//			CustomerMaster customerMaster=cs.getCustomerById(advertisement.getCustomer().getCustId());
//	        advertisement.setCustomer(customerMaster);
			CustomerMaster cm=customerSpringDataDao.findById(cid).get();
			advertisement.setCustomer(cm);
			Staff staff=staffSpringDataDao.findById(sid).get();
			advertisement.setStaff(staff);
			AdvertisementDetails advertisements=
					advertisementDetailSpringDataDaoImpl.save(advertisement);
			System.out.println(advertisements);
			return 1;
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
		
	}
		

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

	@Override
	public AdvertisementDetails updateAdvertisement(AdvertisementDetails advertisement) throws AdvertisementException {
		try {
			AdvertisementDetails advertisements= 
					advertisementDetailSpringDataDaoImpl.save(advertisement);
			return advertisements;
		}catch(DataAccessException dataAccessException) {
			throw new AdvertisementException(dataAccessException.getMessage(),dataAccessException);
		}catch(Exception exception) {
			throw new AdvertisementException(exception.getMessage(),exception);
		}
	}

}
