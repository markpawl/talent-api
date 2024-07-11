package com.example.Talent;

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

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getById(@PathVariable(value="id") Long id) {
		return userRepository.findById(id);
	}
	
    @PostMapping("/users")
    public User create(@RequestBody User user) {
    	User response = userRepository.saveAndFlush(user);
    	return response;
    }	
	
	@PutMapping("/users/{id}")
	public User getById(@PathVariable(value="id") Long id,
			@RequestBody User userUpdates) {
		Optional<User> userToUpdate = userRepository.findById(id);
		if(userToUpdate.isPresent()) {
			userToUpdate.get().setUsername(userUpdates.getUsername());
			userToUpdate.get().setPassword(userUpdates.getPassword());
			userToUpdate.get().setType(userUpdates.getType());
			userRepository.save(userToUpdate.get());
			return userToUpdate.get();
		}
		return null;
	}    
    
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
