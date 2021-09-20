package com.example.onboarding.poc2.redis.model;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash(value = "Cast", timeToLive = 300)
@Data
public class CastModel {
	private String id;
	private String name;
	private String nation;
	
	public CastModel(String id, String name, String nation) {
		this.id = id;
		this.name = name;
		this.nation = nation;
	}
	
}
