package com.example.onboarding.poc2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.redis.model.CastModel;
import com.example.onboarding.poc2.service.CastService;

@RestController
public class CastController {
	
	@Autowired
	CastService castService;
	
	@GetMapping("/all")
	public ResponseDto GetAll() {
		ResponseDto response = new ResponseDto();
		return castService.getAll();
	}

	@GetMapping("/all/{id}")
	public ResponseDto GetAll(@PathVariable("id") final String id) {
		return castService.getId(id);
	}

	@PostMapping("/add")
	public ResponseDto add(@RequestBody CastModel cast) {
		return castService.add(cast);
	}

	@PostMapping("/update")
	public ResponseDto update(@RequestBody CastModel cast) {
		return castService.update(cast);
	}

	@PostMapping("/delete/{id}")
	public ResponseDto delete(@PathVariable("id") final String id) {
		return castService.delete(id);
	}

}
