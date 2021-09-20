package com.example.onboarding.poc2.service;

import com.example.onboarding.poc2.dto.ResponseDto;
import com.example.onboarding.poc2.redis.model.CastModel;

public interface CastService {

	ResponseDto getAll();

	ResponseDto getId(String id);

	ResponseDto add(CastModel cast);

	ResponseDto update(CastModel cast);

	ResponseDto delete(String id);

}
