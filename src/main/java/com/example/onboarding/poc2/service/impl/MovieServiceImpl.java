package com.example.onboarding.poc2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.onboarding.poc2.dao.MovieDao;
import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.model.MovieModel;
import com.example.onboarding.poc2.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieDao movieDao;

	@Override
	public ResponseEntity<List<MovieModel>> getAllMovies(String title) {
		try {
			List<MovieModel> movie = new ArrayList<MovieModel>();

			if (title == null)
				movieDao.findAll().forEach(movie::add);
			else
				movieDao.findByTitle(title).forEach(movie::add);

			if (movie.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(movie, HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto insertMovie(String title, String genre) {
		ResponseDto response = new ResponseDto();
		MovieModel movie = movieDao.save(new MovieModel(title, genre));
		
		response.setCode("200");
		response.setMessage("Insert Success");
		response.setData(movie);
		
		return response;
	}

	@Override
	public ResponseDto updateMovie(MovieModel movie) {
		ResponseDto response = new ResponseDto();
		try {
			MovieModel movies = movieDao.findById(movie.getId()).get();
			movies.setTitle(movie.getTitle());
			movies.setGenre(movie.getGenre());
			movieDao.save(movies);
			response.setCode("200");
			response.setMessage("Update Succes");
			response.setData(movies);
			
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto deleteMovie(Long id) {
		ResponseDto response = new ResponseDto();
		try {
			movieDao.deleteById(id);
			
			response.setCode("200");
			response.setMessage("Delete Succes");
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

}
