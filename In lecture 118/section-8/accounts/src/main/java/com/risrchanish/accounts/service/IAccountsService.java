package com.risrchanish.accounts.service;

import com.risrchanish.accounts.dto.CustomerDto;

public interface IAccountsService {

	
	/*
	 * @Param - CustomerDto
	 * Create - customerDto object
	 */
	
	void createAccount(CustomerDto dto);
	
	/*
	 * @Param - mobileNumber - Input mobile number
	 * @Return Accounts details based on given mobileNumber
	 */
	
	CustomerDto fetchAccount(String mobileNumber);
	
	
	
	/*
	 * @Param - CustomerDto - Input DTO object
	 * @Return boolean if the account is updated or not based on given input
	 */
	
	boolean updateAccount(CustomerDto dto);
	
	
	/*
	 * @Param - mobileNumber - Input Mobile Number
	 * @Return boolean if the account is deleted or not based on given input
	 */
	
	boolean deleteAccount(String mobileNumber);
	
	
}
