package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SequenceGeneratorService;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/upload/{id}")
	public User uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable int id) throws IOException 
	{
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length );
		String folder ="/Users/Lenovo/Desktop/sigFront/SigFrontProject-master/src/assets/";
		byte [] bytes = file.getBytes();
		Path path = Paths.get(folder + file.getOriginalFilename());
		Files.write(path, bytes);
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+id));
		
		user.setImage(file.getOriginalFilename());

		return userRepository.save(user);
		//return ResponseEntity.status(HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/addUser")
	public User saveUser(@RequestBody User user) throws Exception {
		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		String emaiId = user.getEmail();
		if(emaiId != null && !"".equals(emaiId)) {
			User	usertobj = userRepository.findByEmail(emaiId);
		
			if (usertobj != null) {
					throw new Exception("bad credentials");
			}
		}
	
		return userRepository.save(user);


	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getAllUsers")
	public List<User> getUsers(){
		System.out.println("entreeeee**************************");

		return userRepository.findAll();
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getUser/{id}")
	public Optional<User> getUserById(@PathVariable int id){
		return userRepository.findById(id);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Medecin non trouvé avec l'id :"+id));
		userRepository.delete(user);
		
		Map<String,Boolean>  rep = new HashMap<>();
		rep.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(rep);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User userInfo){
		System.out.println("entreeeee**************************");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+id));

		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setRole(userInfo.getRole());
		user.setTelephone(userInfo.getTelephone());
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		//user.setVideos(userInfo.getVideos());

		
		User userAjour = userRepository.save(user);
		return ResponseEntity.ok(userAjour);
	}


}
