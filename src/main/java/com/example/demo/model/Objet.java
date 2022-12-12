package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Objet {
	@Transient
	public static final String SEQUENCE_NAME = "objets_sequence";
	@Id
	private int id;
	private String value;
	private List<Integer> videos = new ArrayList<>() ; //list of ids of videos
	
	public List<Integer> getVideos() {
		return videos;
	}

	
	public void setVideos(List<Integer> videos) {
		this.videos = videos;
	}

	public void addVideo (Integer video) {   
		this.videos.add(video);
	}
	
	public void removeVideo (Integer video) {
		this.videos.remove(video);
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Objet [id=" + id + ", value=" + value + ", videos=" + videos + "]";
	}
	public Objet(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public Objet() {
		super();
	}

	

}
