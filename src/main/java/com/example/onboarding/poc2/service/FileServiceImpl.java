package com.example.onboarding.poc2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.onboarding.poc2.config.Config;
import com.example.onboarding.poc2.dao.MovieDao;
import com.example.onboarding.poc2.model.MovieModel;
import com.example.onboarding.poc2.utils.SftpUtils;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	Config config;
	
	@Autowired
	MovieDao movieDao;

	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
	@Override
	public void uploadFile(MultipartFile file) throws Exception {
		try {
			SftpUtils sftp = new SftpUtils(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

			String filename = file.getOriginalFilename();
			String fileType = file.getContentType();
			byte[] fileContent = file.getBytes();

			String sftpPath = config.getSftpFolder().concat("/").concat(filename);
			sftp.sftpPutFromStream(file.getInputStream(), sftpPath);
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void uploadSave(MultipartFile file) throws Exception {
		BufferedReader br = null;
		FileReader fr = null;
		String line = "";
		try {
//			SftpUtils sftp = new SftpUtils(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
//					config.getSftpPassword(), config.getSftpFolder());
//
//			input = sftp.GetContainFile(config.getSftpFolder().concat("movie.csv"));
//			
//			is = new InputStreamReader(input);
//			br = new BufferedReader(new InputStreamReader(input));
//			
//			while((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
			
			String[] names = file.getOriginalFilename().split("\\.");
			
			File tempFile = File.createTempFile(names[0], names[names.length -1]);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(file.getBytes());
			fos.close();
			
			fr = new FileReader(tempFile);
			br = new BufferedReader(fr);
			
			String[] listString;
			while ((line = br.readLine()) != null ) {
				listString = line.split(",");
				
				movieDao.save(new MovieModel(listString[0], listString[1]));
			}
			
		}catch(Exception e) {
			throw e;
		} finally {
			if(null != br) {
				br.close();
			}
			
			if(null != fr) {
				fr.close();
			}
			
		}
		
	}

	@Override
	public List<String> getFile(String fileName) throws Exception {
		try {
			SftpUtils sftp = new SftpUtils(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());
			
			String sftpPath = config.getSftpFolder().concat("/").concat(fileName);
			
			List<String> res = sftp.sftpGetFiles(sftpPath);
	        return res;
		}catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public Future<Void> InsertMovieFromFile(List<String> arrayList) {
		return executor.submit(() -> {
			String denominator = ",";
            for(String items : arrayList){
                String title = items.split(denominator)[0].replaceAll("\uFEFF", "");
                String genre = items.split(denominator)[1];

                List<MovieModel> list = movieDao.findByTitle(title);
                if(list.size()>0){
                    movieDao.deleteById(list.get(0).getId());
                }
                movieDao.save(new MovieModel(title,genre));
            }
			
            return null;
		});
	}

}
