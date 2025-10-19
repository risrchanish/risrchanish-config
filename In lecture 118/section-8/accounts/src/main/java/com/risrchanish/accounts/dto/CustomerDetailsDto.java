package com.risrchanish.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
		name = "CustomerDetails",
		description = "Schema to hold Customer, Account, Cards and Loans information"
		)
public class CustomerDetailsDto {


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
	
	@Schema(description = "Account details for the customer")
	private AccountsDto accountsDto;
	
	
	@Schema(description = "Cards details for the customer")
	private CardsDto cardsDto;
	
	
	@Schema(description = "Loans details for the customer")
	private LoansDto loansDto;
	

}
