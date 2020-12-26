package com.capgemini.advertisement.service;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.StaffException;


/**
 * 
 * @author Shunottara and Samidha
 *
 */
@Service
@Transactional

public class StaffServiceImpl implements StaffService
{
	@Autowired
	private StaffSpringDataDAO staffSpringDataDaoImpl;

	//calling addStaff method of DAO layer
	@Override
	public Integer addStaff(Staff staff) throws StaffException 
	{
		try 
		{
			staffSpringDataDaoImpl.save(staff);
			return 1;
		}
		catch(DataAccessException dataAccessException) 
		{
			throw new StaffException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new StaffException(exception.getMessage(),exception);
		}

	}



	//calling getStaffById method of DAO layer
	@Override
	public Staff getStaffById(Integer staffId) throws StaffException 
	{
		try
		{            
			Optional<Staff> optional=  staffSpringDataDaoImpl.findById(staffId);
			if(optional.isPresent())
			{
				return optional.get();
			}
			else
			{
				return null;
			}
		}
		catch(DataAccessException dataAccessException) 
		{
			throw new StaffException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new StaffException(exception.getMessage(),exception);
		}
	}


	//calling deleteStaff method of DAO layer
	@Override
	public Integer deleteStaff(Integer staffId) throws StaffException 
	{
		try 
		{            
			staffSpringDataDaoImpl.deleteById(staffId);
			return staffId;
		}
		catch(DataAccessException dataAccessException) 
		{
			throw new StaffException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new StaffException(exception.getMessage(),exception);
		}
	}



	//calling getAllStaff method of DAO layer
	@Override
	public List<Staff> getAllStaff() throws StaffException
	{
		try 
		{            
			List<Staff> staffList= staffSpringDataDaoImpl.findAll();
			return staffList;
		}
		catch(DataAccessException dataAccessException) 
		{
			throw new StaffException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new StaffException(exception.getMessage(),exception);
		}
	}



	//calling updateStaff method of DAO layer
	@Override
	public Staff updateStaff(Staff staff) throws StaffException 
	{
		try 
		{            
			Staff updatedStaff= staffSpringDataDaoImpl.save(staff);    
			return updatedStaff;
		}
		catch(DataAccessException dataAccessException) 
		{
			throw new StaffException(dataAccessException.getMessage(),dataAccessException);
		}
		catch(Exception exception)
		{
			throw new StaffException(exception.getMessage(),exception);
		}
	}



}