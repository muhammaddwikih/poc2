package com.example.onboarding.poc2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

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
	public void uploadSave(MultipartFile file) {
		try {
			String line = "";
			
			File tempFile = new File(file.getOriginalFilename());
			tempFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(file.getBytes());
			fos.close();
			
			BufferedReader br = new BufferedReader(new FileReader(tempFile));
			
			while ((line = br.readLine()) != null ) {
				String[] listString = line.split(",");
				
				MovieModel movie = movieDao.save(new MovieModel(listString[0], listString[1]));
			}
			
		}catch(Exception e) {
			
		}
		
	}

}
