package com.example.onboarding.poc2.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.onboarding.poc2.model.MovieModel;

@DataJpaTest
public class MovieDaoMockJPATest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieDao movieDao;

    @BeforeEach
    public void init() {
    	// given
    	MovieModel movie1 = new MovieModel("Movie Title 1", "Movie Genre 1");
    	entityManager.persist(movie1);
    	
    	MovieModel movie2 = new MovieModel("Movie Title 2", "Movie Genre 2");
        entityManager.persist(movie2);
        
        entityManager.flush();
    }
    
//    @Test
//    public void test_findByTitle() {        
//        // when
//        List<MovieModel> found = movieDao.findByTitle("Movie Title 1");
//        
//        MovieModel result = null;
//        if(found.size() > 0) {
//        	result = found.get(0);
//        }
//
//        // then
//        assertThat(result.getTitle())
//          .isEqualTo("Movie Title 1");
//    }
}
