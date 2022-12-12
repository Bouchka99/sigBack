package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.repository.ObjetRepository;

@RestController
public class ObjetController {
	@Autowired
	private ObjetRepository objetRepository;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService; 
	
	@PostMapping("/addObjet")
	public String saveObjet (@RequestBody Objet objet) {
		objet.setId(sequenceGeneratorService.generateSequence(Objet.SEQUENCE_NAME));
		objetRepository.save(objet);
		return "Objet added successfully";
	}
	
	@GetMapping("/getAllObjets")
	public List<Objet> getObjets(){
		return objetRepository.findAll();
	}
	
	@GetMapping("/getObjet/{id}")
	public Optional <Objet> getVideoById(@PathVariable int id) {
		return objetRepository.findById(id);
	}
	
	@DeleteMapping("/deleteObjet/{id}")
	public String deleteObjet(@PathVariable int id) {
		objetRepository.deleteById(id);
		return "objet deleted successfully";
	}
	
	@PutMapping("/updateObjet/{id}")
	public ResponseEntity<Objet> updateObjet(@PathVariable int id, @RequestBody Objet objetInfo) {
		Objet objet = objetRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException ("Objet non trouvé avec l'id :"+id));
		objet.setValue(objetInfo.getValue());
		Objet objetAjour = objetRepository.save(objet);
		return ResponseEntity.ok(objetAjour);
	}
	

}
