package com.example.demo.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Generated;

@Document(collection="Video")
public class Video {
    @Transient
    public static final String SEQUENCE_NAME = "videos_sequence";
	@Id
	private int id;
	
	private int idUser;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	private String lien;
	
	private double lng;
	private double lat;
	
	@JsonIgnore
	@DocumentReference(lazy=true)
	private User owner;
	
	
	private Objet objet;
	
	
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Objet getObjet() {
		return objet;
	}
	public void setObjet(Objet objet) {
		this.objet = objet;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Video() {
		super();
	}
	public Video(String lien) {
		super();
		this.lien = lien;
		//this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", lien=" + lien+ "]";
	}
	

}
