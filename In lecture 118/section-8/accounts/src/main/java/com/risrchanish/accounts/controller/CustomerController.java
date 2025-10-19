package com.risrchanish.accounts.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.risrchanish.accounts.dto.CustomerDetailsDto;
import com.risrchanish.accounts.dto.ErrorResponseDto;
import com.risrchanish.accounts.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(
		name = "CRUD REST APIs for customer risrchanish",
		description = "CREATE, UPDATE, FETCH, DELETE operations in risrchanish"
		)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

	
	private final ICustomerService iCustomerService;
	
	public CustomerController(ICustomerService iCustomerService)
	{
		this.iCustomerService = iCustomerService;
	}
	
	
	@Operation(
			summary = "Fetch Customer REST API",
			description = "REST API to FETCH customer details based on a mobilenumber"
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
	
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam 
											@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be of 10 digits")
											String mobileNumber)
	{
		CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto); 
	}
}
