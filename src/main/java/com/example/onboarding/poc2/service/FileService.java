package com.example.onboarding.poc2.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	void uploadFile(MultipartFile file) throws Exception;

	void uploadSave(MultipartFile file);

}
