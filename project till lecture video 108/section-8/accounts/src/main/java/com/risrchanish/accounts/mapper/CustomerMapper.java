package com.risrchanish.accounts.mapper;

import com.risrchanish.accounts.dto.CustomerDto;
import com.risrchanish.accounts.entity.Customer;

public class CustomerMapper {

	public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto dto)
	{
		dto.setEmail(customer.getEmail());
		dto.setMobileNumber(customer.getMobileNumber());
		dto.setName(customer.getName());
		
		return dto;
	}
	
	
	public static Customer mapToCustomer(Customer customer, CustomerDto dto)
	{
		customer.setEmail(dto.getEmail());
		customer.setMobileNumber(dto.getMobileNumber());
		customer.setName(dto.getName());
		
		return customer;
	}
}
