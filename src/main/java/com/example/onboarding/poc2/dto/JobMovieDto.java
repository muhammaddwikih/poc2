package com.example.onboarding.poc2.dto;

import com.example.onboarding.poc2.model.MovieModel;

import lombok.Data;

@Data
public class JobMovieDto {
	
	private MovieModel movie;
	private JobDto job;

}
