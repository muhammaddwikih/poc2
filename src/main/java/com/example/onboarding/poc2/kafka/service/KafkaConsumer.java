package com.example.onboarding.poc2.kafka.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.onboarding.poc2.model.MovieModel;
import com.example.onboarding.poc2.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class KafkaConsumer {

	@Autowired
	MovieService movieService;

	@KafkaListener(topics = "${spring.custom.kafka.topic}", groupId = "java")
	public void listToTopicOnBoaarding(String message) throws JsonMappingException, JsonProcessingException {
		
		 ObjectMapper mapper = new ObjectMapper();
         MovieModel dto = mapper.readValue(message, MovieModel.class);

		movieService.insertMovie(dto.getTitle(), dto.getGenre());

	}
}
