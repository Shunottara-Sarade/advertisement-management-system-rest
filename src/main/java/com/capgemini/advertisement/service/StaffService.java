package com.capgemini.advertisement.service;

import java.util.List;

import com.capgemini.advertisement.entity.Staff;
import com.capgemini.advertisement.exception.StaffException;

public interface StaffService 
{
	/**
	 * add staff
	 * @param staff
	 * @return 1 if staff added
	 * else
	 * @throws StaffException
	 */
	public Integer addStaff(Staff staff) throws StaffException;

	/**
	 * get staff by id
	 * @param staffId
	 * @return staff
	 * @throws StaffException
	 */
	public Staff getStaffById(Integer staffId) throws StaffException;

	/**
	 * delete staff
	 * @param staffId
	 * @return 1 if staff deleted
	 * else
	 * @throws StaffException
	 */
	public Integer deleteStaff(Integer staffId) throws StaffException;

	/**
	 * get all staff
	 * @return list of all staff
	 * @throws StaffException
	 */
	public List<Staff> getAllStaff() throws StaffException;

	/**
	 * update staff
	 * @param staff
	 * @return staff
	 * @throws StaffException
	 */
	public Staff updateStaff(Staff staff) throws StaffException;
}
