package com.example.onboarding.poc2.redis.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.onboarding.poc2.redis.model.CastModel;

@Repository
public interface CastDao extends CrudRepository<CastModel, String> {

}
