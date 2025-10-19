package com.risrchanish.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;



@Schema(
		name = "Accounts",
		description = "Schema to hold account information"
		)
public class AccountsDto {
	
	

	@Schema(
		description = "Account number of risrchanish"
		)
	@NotEmpty(message = "Accounty number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Account number must be of 10 digit")
	private Long accountNumber;
	
	
	@Schema(
			description = "Account type of risrchanish", example = "savings"
			)
	@NotEmpty(message = "Account type is required")
	private String accountType;
	
	
	@Schema(
			description = "Account branch address of risrchanish"
			)
	@NotEmpty(message = "Branch address is required")
	private String branchAddress;
	
	
	public AccountsDto()
	{}
	
	
	
	public AccountsDto(Long accountNumber, String accountType, String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}


	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	
	
	
}
