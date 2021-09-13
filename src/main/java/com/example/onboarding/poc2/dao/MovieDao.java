package com.example.onboarding.poc2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.poc2.model.MovieModel;

import java.util.List;

public interface MovieDao extends JpaRepository<MovieModel, Long> {
	
	List<MovieModel> findByTitle(String title);
}
