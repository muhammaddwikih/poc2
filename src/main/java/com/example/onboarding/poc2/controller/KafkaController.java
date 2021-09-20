package com.example.onboarding.poc2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.kafka.service.KafkaProducer;
import com.example.onboarding.poc2.model.MovieModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
    @RequestMapping(value = "/sendMessage", method=RequestMethod.POST)
    public ResponseDto sendMessage(@RequestBody MovieModel dto) {
    	ResponseDto response = new ResponseDto();
    	try {
            ObjectMapper mapper = new ObjectMapper();
            String stringified = mapper.writeValueAsString(dto);

			kafkaProducer.sendMessage(stringified);

            response.setCode("200");
            response.setMessage("Message Succes");
			return response;
		} catch (Exception e) {
			response.setCode("500");
            response.setMessage("Message Gagal");
            return response;
		}
    }

}
