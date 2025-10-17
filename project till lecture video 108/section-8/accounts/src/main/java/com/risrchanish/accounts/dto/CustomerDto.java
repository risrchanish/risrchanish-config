package com.risrchanish.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
		name = "Customer",
		description = "Schema to hold customer and account information"
		)
public class CustomerDto {

	
	@Schema(
			description = "Name of the customer", example = "risrchanish"
			)
	@NotEmpty(message = "Name is required")
	@Size(min = 5,max=30,message="The length of the customer name should be between 5 and 30")
	private String name;
	
	
	@Schema(
			description = "Email address of the customer", example = "risrchanish@gmail.com"
			)
	@NotEmpty(message = "Email is required")
	@Email(message = "Email address should be valid")
	private String email;
	
	
	@Schema(
			description = "Mobile number of the customer", example = "9999999999"
			)
	@NotEmpty(message = "Mobile number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
	private String mobileNumber;
	
	private AccountsDto accountsDto;
	
	public CustomerDto()
	{
		
	}
	
	public CustomerDto(String name, String email, String mobileNumber, AccountsDto accountsDto) {
		
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountsDto = accountsDto;
	}
	
	
	
	public AccountsDto getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountsDto="
				+ accountsDto + "]";
	}
	

	
}
