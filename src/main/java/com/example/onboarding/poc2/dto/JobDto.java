package com.example.onboarding.poc2.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Data;

@Data
public class JobDto {
	private LocalDateTime dateTime;
	private String type;
	private ZoneId timeZone;

}
