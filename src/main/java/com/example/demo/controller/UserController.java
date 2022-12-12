package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/addUser")
	public String saveUser(@RequestBody User user) {
		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		userRepository.save(user);
		return "User added sucessfully with User_id:";
		
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getUsers(){
		System.out.println("entreeeee**************************");

		return userRepository.findAll();
		
	}
	
	@GetMapping("/getUser/{id}")
	public Optional<User> getUserById(@PathVariable int id){
		return userRepository.findById(id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return "User deleted successfully with user_id:";
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User userInfo){
		System.out.println("entreeeee**************************");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("User non trouvé avec l'id :"+id));

		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setRole(userInfo.getRole());
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		//user.setVideos(userInfo.getVideos());

		
		User userAjour = userRepository.save(user);
		return ResponseEntity.ok(userAjour);
	}


}
