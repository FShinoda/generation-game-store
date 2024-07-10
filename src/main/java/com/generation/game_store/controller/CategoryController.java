package com.generation.game_store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.game_store.model.Category;
import com.generation.game_store.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins="*", allowedHeaders="*")
public class CategoryController{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id){
		return categoryRepository.findById(id)
				.map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Category> post(@Valid @RequestBody Category category){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoryRepository.save(category));
	}
	
	@PutMapping
	public ResponseEntity<Category> put(@Valid @RequestBody Category category){
		return categoryRepository.findById(category.getId())
				.map(response -> ResponseEntity.status(HttpStatus.CREATED)
						.body(categoryRepository.save(category)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		categoryRepository.deleteById(id);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<List<Category>> getByName(@PathVariable String name){
		return ResponseEntity.ok(categoryRepository.findAllByNameContainingIgnoreCase(name));
	}

}
