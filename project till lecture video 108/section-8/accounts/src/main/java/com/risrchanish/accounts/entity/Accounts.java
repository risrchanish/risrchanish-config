package com.risrchanish.accounts.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "accounts")
public class Accounts extends BaseEntity{

	private Long customerId;
	
	
	
	@Id
	private Long accountNumber;
	private String accountType;
	private String branchAddress;
	
	public Accounts()
	{
		
	}
	
	
	public Accounts(Long customerId, Long accountNumber, String accountType, String branchAddress) {
		super();
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}


	

	public String getBranchAddress() {
		return branchAddress;
	}


	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}


	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	@Override
	public String toString() {
		return "Accounts [customerId=" + customerId + ", accountNumber=" + accountNumber + ", accountType="
				+ accountType + "]";
	}
	
	
	
}
