package com.example.onboarding.poc2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.model.MovieModel;
import com.example.onboarding.poc2.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/get-movies")
	public ResponseEntity<List<MovieModel>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			return movieService.getAllMovies(title);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/insert")
	public ResponseDto insert(@RequestBody MovieModel movie) {
		ResponseDto response = new ResponseDto();
		try {
			return movieService.insertMovie(movie.getTitle(), movie.getGenre());
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}
	
	@PutMapping("/update")
    public ResponseDto updateMovie(@RequestBody MovieModel movie) {
        return movieService.updateMovie(movie);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseDto deleteMovie(@PathVariable("id") Long id) {
        return movieService.deleteMovie(id);
    }
	
	
}
