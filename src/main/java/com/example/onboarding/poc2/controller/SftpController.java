package com.example.onboarding.poc2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.service.FileService;


@RestController
@RequestMapping("/sftp")
public class SftpController {

	@Autowired
	FileService fileService;

//	@ApiParam(allowMultiple=true) 
	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDto upload(@RequestPart(value = "file") MultipartFile file) {
		ResponseDto response = new ResponseDto();
		try {
			fileService.uploadFile(file);

			response.setCode("200");
			response.setMessage("Upload Success");

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Upload Failed");
			return response;
		}
	}
	
	@RequestMapping(path = "/upload-save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseDto uploadSave(@RequestPart(value = "file") MultipartFile file) {
		ResponseDto response = new ResponseDto();
		try {
			fileService.uploadSave(file);

			response.setCode("200");
			response.setMessage("Upload Success");

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Upload Failed");
			return response;
		}
	}

}
