package com.example.onboarding.poc2.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.model.MovieModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

	@LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;
    
//    @BeforeEach
//    public void init() {
//    	url = String.format("http://localhost:%d/movies/insert", port);
//    	MovieModel request = new MovieModel("test title junit","test genre junit");
//    	
//    	ResponseDto result = this.restTemplate.postForObject(url, request, ResponseDto.class);
//    }
    
    @Test
    public void test_getMovie() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	MovieModel request = new MovieModel("test title junit","test genre junit");
    	
    	ResponseDto result1 = this.restTemplate.postForObject(url, request, ResponseDto.class);
    	
    	url = String.format("http://localhost:%d/movies/get-movies", port);
    	Map<String, String> testing = new HashMap();
    	testing.put("title", "test title junit");
    	
    	ResponseDto result = this.restTemplate.getForObject(url, ResponseDto.class, testing);
    	assertThat(result.getCode().equals("200"));
    }

    
    @Test
    public void test_insert() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	MovieModel request = new MovieModel("test title junit save","test genre junit save");
    	
    	ResponseDto result = this.restTemplate.postForObject(url, request, ResponseDto.class);
    	assertThat(result.getCode().equals("200"));
    }
    
    
    @Test
    public void test_delete() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	MovieModel requestSave = new MovieModel("test title junit","test genre junit");
    	
    	ResponseDto dto = this.restTemplate.postForObject(url, requestSave, ResponseDto.class);
    	
    	url = String.format("http://localhost:%d/movies/delete", port);
    	Map<String, Long> testing = new HashMap();
    	testing.put("id", 1l);
    	
    	this.restTemplate.delete(url, testing);
    	
    	url = String.format("http://localhost:%d/movies/get-movies", port);
    	Map<String, String> testings = new HashMap();
    	testings.put("title", "test title junit");
    	
    	ResponseDto result = this.restTemplate.getForObject(url, ResponseDto.class, testings);
    	assertThat(result.getCode().equals("200"));
    }
    
    @Test
    public void test_update() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	MovieModel requestSave = new MovieModel("test title junit","test genre junit");
    	
    	this.restTemplate.postForObject(url, requestSave, ResponseDto.class);
    	
    	url = String.format("http://localhost:%d/movies/update", port);
    	MovieModel request = new MovieModel(1L,"test title junit Edit","test genre junit");
    	
    	this.restTemplate.put(url, request);
    	
    	url = String.format("http://localhost:%d/movies/get-movies", port);
    	Map<String, String> testing = new HashMap();
    	testing.put("title", "test title junit Edit");
    	
    	ResponseDto result = this.restTemplate.getForObject(url, ResponseDto.class, testing);
    	assertThat(result.getCode().equals("200"));
    }
    
}
