package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Objet;
import com.example.demo.model.SequenceGeneratorService;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.ObjetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;

@RestController
public class VideoController {
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjetRepository objetRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/addVideo/{idUser}/{idObjet}")
	public Video saveVideo(@RequestBody Video video,@PathVariable int idUser,@PathVariable int idObjet) {
		
		Objet objet =objetRepository.findById(idObjet)
				.orElseThrow(() -> new ResourceNotFoundException ("Objet non trouvé avec l'id :"+idObjet));
		
		User user =userRepository.findById(idUser)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+idUser));
		
		//video.setOwner(user);
		video.setObjet(objet);
		video.setId(sequenceGeneratorService.generateSequence(Video.SEQUENCE_NAME));
		video.setIdUser(idUser);
		//video.setOwner(user);    //we cannot get becides because it will be an infinity loop
		
		user.addVideo(video);
		userRepository.save(user);
		
		objet.addVideo(video.getId());
		objetRepository.save(objet);

		return videoRepository.save(video);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getUserByvideos")
	public User getUserByvideos(@PathVariable int id) throws Exception{
		
		List<User> Users = userRepository.findAll();
		try {
			for(int i =0 ; i<Users.size();i++) {
				List<Video> Videos = Users.get(i).getVideos();
				
				for(int j =0 ; j<Videos.size();j++) {
					if(id == Videos.get(i).getId()) {
						System.out.println(Users.get(i));
						return Users.get(i);
					}
					
				}
			}
			}
			catch(Exception e) {
				throw new Exception("bad credentials");
			}
		return null;
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAllVideos")
	public List<Video> getVideos(){
		return videoRepository.findAll();
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getVideo/{id}")
	public Optional<Video> getVideoById(@PathVariable int id){
		return videoRepository.findById(id);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/deleteVideo/{idVideo}/{idUser}")
	public String deleteVideo(@PathVariable int idVideo,@PathVariable int idUser) {
		Video video = videoRepository.findById(idVideo)
				.orElseThrow(() -> new ResourceNotFoundException ("Video non trouvé avec l'id :"+idVideo));;
		videoRepository.deleteById(idVideo);
		User user =userRepository.findById(idUser)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+idUser));
		user.removeVideo(video);
		
		userRepository.save(user);
		return ("Video deleted successfully with video_id:"+idVideo);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateVideo/{idVideo}/{idUser}")
	public ResponseEntity<Video> updateVideo(@PathVariable int idVideo,@PathVariable int idUser, @RequestBody Video videoInfo){
		Video video = videoRepository.findById(idVideo)
				.orElseThrow(() -> new ResourceNotFoundException ("Video non trouvé avec l'id :"+idVideo));
		User user = userRepository.findById(idUser)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+idUser));
		

		video.setLien(videoInfo.getLien());
		video.setOwner(videoInfo.getOwner());
		
		Video videoAjour = videoRepository.save(video);
		return ResponseEntity.ok(videoAjour);
	}

}
