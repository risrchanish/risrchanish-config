package com.risrchanish.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.risrchanish.accounts.constants.AccountsConstants;
import com.risrchanish.accounts.dto.AccountsDto;
import com.risrchanish.accounts.dto.CustomerDto;
import com.risrchanish.accounts.entity.Accounts;
import com.risrchanish.accounts.entity.Customer;
import com.risrchanish.accounts.exception.CustomerAlreadyExistsException;
import com.risrchanish.accounts.exception.ResourceNotFoundException;
import com.risrchanish.accounts.mapper.AccountsMapper;
import com.risrchanish.accounts.mapper.CustomerMapper;
import com.risrchanish.accounts.repository.AccountRepository;
import com.risrchanish.accounts.repository.CustomerRepository;
import com.risrchanish.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService{

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	
	
	public AccountsServiceImpl(AccountRepository accountRepository,CustomerRepository customerRepository)
	{
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}
	
	@Override
	public void createAccount(CustomerDto dto) {
		
		Customer customer = CustomerMapper.mapToCustomer(new Customer(), dto);
		
		Optional <Customer> optionalCustomer = customerRepository.findByMobileNumber(dto.getMobileNumber());
		
		if(optionalCustomer.isPresent())
		{
			throw new CustomerAlreadyExistsException("Mobile number already present");
		}
		
		Customer savedCustomer = customerRepository.save(customer);
		
		accountRepository.save(createNewAccount(savedCustomer));
		
		
	}
	
	private Accounts createNewAccount(Customer customer)
	{
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		
		Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		
		
		
		return newAccount;
	}
	
	/*
	 * @Param - mobileNumber - Input mobile number
	 * @Return Accounts details based on given mobileNumber
	 */

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("accounts", "customerId", customer.getCustomerId().toString()));
		
		
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		
		customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(accounts, new AccountsDto()));
		
		return customerDto;
	}
	
	/*
	 * @Param - CustomerDto - Input DTO object
	 * @Return boolean if the account is updated or not based on given input
	 */
	

	@Override
	public boolean updateAccount(CustomerDto dto) {
		
		boolean isUpdated = false;
		
		AccountsDto accountsDto = dto.getAccountsDto();
		
		if(accountsDto != null)
		{
			Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Account","AccountNumber",
																accountsDto.getAccountNumber().toString()));
			
			AccountsMapper.maptoAccounts(accountsDto, accounts);
			accounts = accountRepository.save(accounts);
			
			
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer","CustomerId",customerId.toString()));
			
			CustomerMapper.mapToCustomer(customer, dto);
			customerRepository.save(customer);
			isUpdated = true;
		}
		
		return isUpdated;
	}
	
	/*
	 * @Param - mobileNumber - Input Mobile Number
	 * @Return boolean if the account is deleted or not based on given input
	 */
	

	@Override
	public boolean deleteAccount(String mobileNumber) {
		
		Customer customer  = customerRepository.findByMobileNumber(mobileNumber)
								.orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
		
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		
		customerRepository.deleteById(customer.getCustomerId());
		
		return true;
	}
	
	
	

}
