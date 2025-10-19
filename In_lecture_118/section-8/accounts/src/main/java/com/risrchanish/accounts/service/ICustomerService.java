package com.risrchanish.accounts.service;

import com.risrchanish.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
