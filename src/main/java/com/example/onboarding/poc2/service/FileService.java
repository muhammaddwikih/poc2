package com.example.onboarding.poc2.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

	void uploadFile(MultipartFile file) throws Exception;

	void uploadSave(MultipartFile file) throws IOException, Exception;

	List<String> getFile(String fileName) throws Exception;

	Future<Void> InsertMovieFromFile(List<String> arrayList);

}
