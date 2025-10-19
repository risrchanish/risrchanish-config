package com.risrchanish.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.risrchanish.accounts.dto.AccountsDto;
import com.risrchanish.accounts.dto.CardsDto;
import com.risrchanish.accounts.dto.CustomerDetailsDto;
import com.risrchanish.accounts.dto.LoansDto;
import com.risrchanish.accounts.entity.Accounts;
import com.risrchanish.accounts.entity.Customer;
import com.risrchanish.accounts.exception.ResourceNotFoundException;
import com.risrchanish.accounts.mapper.AccountsMapper;
import com.risrchanish.accounts.mapper.CustomerMapper;
import com.risrchanish.accounts.repository.AccountRepository;
import com.risrchanish.accounts.repository.CustomerRepository;
import com.risrchanish.accounts.service.ICustomerService;
import com.risrchanish.accounts.service.client.CardsFeignClient;
import com.risrchanish.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

	
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	
	
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) 
	{
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
	
		// accounts
		
		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
			.orElseThrow(() -> new ResourceNotFoundException("accounts", "customerId", customer.getCustomerId().toString()));
	
	
		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
	
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountDto(accounts, new AccountsDto()));
		
		
		// loans
		
		
		ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
			
		customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
		
		
		// Cards
		
		
		ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
		
		customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
	
		
		return customerDetailsDto;
		
	}

	
}
