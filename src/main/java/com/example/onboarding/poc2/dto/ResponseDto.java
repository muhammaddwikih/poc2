package com.example.onboarding.poc2.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private String code;
	private String message;
	private Object data;
}
