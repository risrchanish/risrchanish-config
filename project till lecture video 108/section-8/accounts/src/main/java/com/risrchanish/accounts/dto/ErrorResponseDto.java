package com.risrchanish.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(
		name = "Error response",
		description = "Schema to hold ERROR response information"
		)
public class ErrorResponseDto {

	@Schema(
			description = "API path invoked by client"
			)
	private String apiPath;
	
	

	@Schema(
			description = "Error code representing the error happend"
			)
	private HttpStatus errorCode;
	
	

	@Schema(
			description = "Error message representing the error happend"
			)
	private String errorMsg;
	
	

	@Schema(
			description = "Error time representing when the error occured"
			)
	private LocalDateTime errorTime;
	
	public ErrorResponseDto()
	{
		
	}

	public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMsg, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorTime = errorTime;
	}

	public String getApiPath() {
		return apiPath;
	}


	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}


	public HttpStatus getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public LocalDateTime getErrorTime() {
		return errorTime;
	}


	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}


	@Override
	public String toString() {
		return "ErrorResponseDto [apiPath=" + apiPath + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", errorTime=" + errorTime + "]";
	}
	
	
}
