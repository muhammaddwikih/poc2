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
	
	@Value("${spring.custom.redis.host}")
	private String redisHost;
	
	@Value("${spring.custom.redis.port}")
	private Integer redisPort;

	@Value("${spring.custom.redis.password}")
	private String redisPassword;

	@Value("${spring.custom.redis.database}")
	private Integer redisDatabase;

	@Value(value = "${spring.custom.kafka.bootstrapAddress}")
	private String kafkaBootstrapAddress;

	@Value(value = "${spring.custom.kafka.topic}")
	private String kafkaTopic;
}
