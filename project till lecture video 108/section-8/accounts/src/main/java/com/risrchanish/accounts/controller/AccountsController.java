package com.risrchanish.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.risrchanish.accounts.constants.AccountsConstants;
import com.risrchanish.accounts.dto.CustomerDto;
import com.risrchanish.accounts.dto.ErrorResponseDto;
import com.risrchanish.accounts.dto.ResponseDto;
import com.risrchanish.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(
		name = "CRUD REST APIs for risrchanish",
		description = "CREATE, UPDATE, FETCH, DELETE operations in risrchanish"
		)
@RestController
@RequestMapping(path = "/accounts/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
	 
	private IAccountsService iAccountService;
	
	public AccountsController(IAccountsService iAccountService)
	{
		this.iAccountService = iAccountService;
	}
	
	
	
	// POST API	
	@Operation(
			summary = "Create Account REST API",
			description = "REST API to create new customer and account in risrchanish"
			)
	@ApiResponse(
			responseCode = "201",
			description = " Http status CREATED"
			)
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto dto)
	{
		
		iAccountService.createAccount(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGHE_201));
	}
	
	
	
	// GetAPI
	
	
	@Operation(
			summary = "Fetch Account REST API",
			description = "REST API to FETCH new customer and account in risrchanish"
			)
	@ApiResponse(
			responseCode = "200",
			description = " Http status OK"
			)
	
	@GetMapping("/fetch") 	// http://localhost:9090/accounts/api/fetch?mobileNumber=9999999999
	public ResponseEntity<CustomerDto> fetchAccountDetails( @RequestParam 
			@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be of 10 digits")
			String mobileNumber)
	{
		CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	
	
	
	// Update API
	
	@Operation(
			summary = "Update Account REST API",
			description = "REST API to UPDATE new customer and account in risrchanish"
			)
	@ApiResponses
			({
				@ApiResponse(responseCode = "200",
							description = " Http status OK"),
				
				@ApiResponse(responseCode = "500",
							description = " Http status Internal Server Error",
							content = @Content(
									schema = @Schema(implementation = ErrorResponseDto.class)
									))
	
			})
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto dto)
	{
		boolean isUpdated = iAccountService.updateAccount(dto);
		
		if(isUpdated)
		{
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}
	
	
	
	// Delete API 	url: http://localhost:9090/accounts/api/delete?mobileNumber=9999999988
	
	@Operation(
			summary = "Delete Account REST API",
			description = "REST API to DELETE new customer and account in risrchanish"
			)
	@ApiResponses
			({
				@ApiResponse(responseCode = "200",
							description = " Http status OK"),
				
				@ApiResponse(responseCode = "500",
							description = " Http status Internal Server Error")
	
			})
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 
			@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be of 10 digits")
			String mobileNumber)
	{
		boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
		
		if(isDeleted)
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}
	
	
}
