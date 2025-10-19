package com.risrchanish.accounts.mapper;

import com.risrchanish.accounts.dto.AccountsDto;
import com.risrchanish.accounts.entity.Accounts;

public class AccountsMapper {

	public static AccountsDto mapToAccountDto(Accounts accounts, AccountsDto dto)
	{
		dto.setAccountNumber(accounts.getAccountNumber());
		dto.setAccountType(accounts.getAccountType());
		dto.setBranchAddress(accounts.getBranchAddress());
		
		return dto;
	}
	
	
	public static Accounts maptoAccounts(AccountsDto dto, Accounts accounts)
	{
		accounts.setAccountNumber(dto.getAccountNumber());
		accounts.setAccountType(dto.getAccountType());
		accounts.setBranchAddress(dto.getBranchAddress());
		
		return accounts;
	}
}
