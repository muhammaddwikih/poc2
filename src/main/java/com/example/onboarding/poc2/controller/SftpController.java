package com.example.onboarding.poc2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.model.MovieModel;
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
	
	@PostMapping("/insert-future")
	public ResponseDto insertUsingFuture(@RequestBody String fileName) {
		ResponseDto response = new ResponseDto();
		try {
			List<String> list = fileService.getFile(fileName);
			
			Future<Void> future1 = fileService.InsertMovieFromFile(new ArrayList<>(list.subList(0, (list.size()) / 2)));
            Future<Void> future2 = fileService.InsertMovieFromFile(new ArrayList<>(list.subList((list.size()) / 2, list.size())));
			
			while (!(future1.isDone() && future2.isDone())) {
			    if(future1.isDone()) {
			    	System.out.println("future1 is done");
			    } else {
			    	System.out.println("future1 is not done");
			    }
			    if(future2.isDone()) {
			    	System.out.println("future2 is done");
			    }
			    else {
			    	System.out.println("future2 is not done");
			    }
			    
//				System.out.println(
//			      String.format(
//			        "future1 is %s and future2 is %s and future3 is %s", 
//			        future1.isDone() ? "done" : "not done", 
//			        future2.isDone() ? "done" : "not done",
//			        future3.isDone() ? "done" : "not done"
//			      )
//			    );
			    Thread.sleep(300);
			}
			
			response.setCode("200");
			response.setMessage("Insert Success");
			
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}
	

}
