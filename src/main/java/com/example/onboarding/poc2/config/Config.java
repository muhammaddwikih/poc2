package com.example.onboarding.poc2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ComponentScan
@Data
public class Config {
	
	@Value("${spring.custom.sftpHost}")
	private String sftpHost;

	@Value("${spring.custom.sftpPort}")
	private Integer sftpPort;

	@Value("${spring.custom.sftpUsername}")
	private String sftpUsername;
	
	@Value("${spring.custom.sftpPassword}")
	private String sftpPassword;

	@Value("${spring.custom.sftpFolder}")
	private String sftpFolder;
	
}
