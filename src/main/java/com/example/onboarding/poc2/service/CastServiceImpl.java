package com.example.onboarding.poc2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.redis.dao.CastDao;
import com.example.onboarding.poc2.redis.model.CastModel;

@Service
public class CastServiceImpl implements CastService{
	
	@Autowired
	CastDao castDao;

	@Override
	public ResponseDto getAll() {
		ResponseDto response = new ResponseDto();
		Iterable<CastModel> model;
		try {
			model = castDao.findAll();
			
			response.setData(model);
			response.setCode("200");
			response.setMessage("Success get all data");
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto getId(String id) {
		ResponseDto response = new ResponseDto();
		Optional<CastModel> model;
		try {
			model = castDao.findById(id);
			
			response.setCode("200");
			response.setData(model);
			response.setMessage("Succes get data by id");
			
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto add(CastModel cast) {
		ResponseDto response = new ResponseDto();
		CastModel model;
		try {
			model = castDao.save(new CastModel(cast.getId(), cast.getName(), cast.getNation()));
			
			response.setCode("200");
			response.setData(model);
			response.setMessage("Success add new data");
			
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto update(CastModel cast) {
		ResponseDto response = new ResponseDto();
		CastModel model;
		try {
			
			model = castDao.findById(cast.getId()).get();
			model.setName(cast.getName());
			model.setNation(cast.getNation());
			
			castDao.save(model);
			
			response.setCode("200");
			response.setData(model);
			response.setMessage("Succes update data");
			
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ResponseDto delete(String id) {
		ResponseDto response = new ResponseDto();
		try {
			castDao.deleteById(id);
			
			response.setCode("200");
			response.setMessage("Delete Succes");
			return response;
		}catch(Exception e) {
			throw e;
		}
	}

}
