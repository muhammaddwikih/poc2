package com.example.onboarding.poc2.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class UserDto extends User {
	private String nickName;
	private String nrp;
	private String divisi;

	public UserDto(String username, Collection<? extends GrantedAuthority> authorities, String nickName, String nrp, String divisi) {
		super(username, "---", authorities);
		this.nickName = nickName;
		this.nrp = nrp;
		this.divisi = divisi;
	}

}
