package com.capgemini.advertisement.entity;

/**
 * 
 * @author Shweta and Sandhya
 *
 */

public class Login
{
	private Integer custId;
	private String password;

	public Integer getCustId()
	{
		return custId;
	}

	public void setCustId(Integer custId) 
	{
		this.custId = custId;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
}