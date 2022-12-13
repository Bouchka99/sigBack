package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Document(collection="User")   //for mongodb
public class User {
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String role;
	private String telephone;
	
	@DocumentReference
	private List<Video> videos;

	
	
	
	
	private String image;//for image of user
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public List<Video> getVideos() {
		return videos;
	}




	public void addVideo (Video video) {
		this.videos.add(video);
	}
	
	public void removeVideo (Video video) {
		this.videos.remove(video);
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}




	public User(int id, String firstName, String lastName, String email, String password, String role , String telephone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password =password;
		this.role =role;
		this.telephone=telephone;
	}
	
	

	
	/*public List<Video> getVideos() {
		return videos;
	}




	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}*/




	public String getTelephone() {
		return telephone;
	}




	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}




	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	//private List<Video> videos; //a compléter
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	public User() {
		super();
	}
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
