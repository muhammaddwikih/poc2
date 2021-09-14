package com.example.onboarding.poc2.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.model.MovieModel;

public interface MovieService {

	ResponseDto getAllMovies(String title);

	ResponseDto insertMovie(String title, String genre);

	ResponseDto updateMovie(MovieModel movie);

	ResponseDto deleteMovie(Long id);

}
