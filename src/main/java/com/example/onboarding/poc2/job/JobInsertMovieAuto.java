package com.example.onboarding.poc2.job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.onboarding.poc2.config.Config;
import com.example.onboarding.poc2.dao.MovieDao;
import com.example.onboarding.poc2.service.MovieService;
import com.example.onboarding.poc2.utils.SftpUtils;


@Component
public class JobInsertMovieAuto implements Job{
	private static final Logger logger = LoggerFactory.getLogger(JobInsertMovieAuto.class);

	@Autowired
	Config config;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	MovieDao movieDao;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		BufferedReader br = null;
		FileReader fr = null;
		String line = "";
		InputStream input = null;
		InputStreamReader is = null;
		try {
			logger.info("Executing Job with key {}", context.getJobDetail().getKey());
			
			String formattedDate = new SimpleDateFormat("yyyyMMdd_hh:mm:ss").format(new Date());
			
			SftpUtils sftp = new SftpUtils(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

			input = sftp.GetContainFile(config.getSftpFolder().concat("movie.csv"));
			
			is = new InputStreamReader(input);
			br = new BufferedReader(new InputStreamReader(input));
			
			String[] listString;
//			while((line = br.readLine()) != null) {
//				listString = line.split(",");
//				
//				List<MovieModel> movie = movieDao.findByTitle(listString[0]);
//				
//				if(movie.size() > 0 ) {
//					movieDao.deleteById(movie.get(0).getId());
//				}else {
//					movieDao.save(new MovieModel(listString[0], listString[1]));
//				}
//				
//			}
//			String title = String.format("Test auto title %s", formattedDate);
//			String genre = String.format("Test auto genre %s", formattedDate);
//
//			movieService.insertMovie(title, genre);
		} catch (Exception e) {
			logger.error("Failed to send insert.", e);
		}finally {
			if(null != br) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(null != fr) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != input) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

}
