package com.example.onboarding.poc2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class MovieModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "genre")
	private String genre;

	public MovieModel() {

	}
	
	public MovieModel(Long id, String title, String genre) {
		this.id = id;
		this.title = title;
		this.genre = genre;
	}
	
	public MovieModel(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}

//	public long getId() {
//		return id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public String getGenre() {
//		return genre;
//	}
//
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}
//
//
//	public void setTitle(String title) {
//		this.title = title;
//	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", genre=" + genre + "]";
	}

}
