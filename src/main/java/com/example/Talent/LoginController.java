package com.example.Talent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	UserRepository userRepository;
	
    @PostMapping("/login")
    public ResponseEntity<User> create(@RequestBody Credentials credentials) {
    	User user = userRepository.findByUsername(credentials.getUsername());
    	if(user == null) {
    		return ResponseEntity.status(401).body(null);
    	}
    	if(user.getPassword().equalsIgnoreCase(credentials.getPassword() )) {
    		return ResponseEntity.status(200).body(user);
    	}else {
    		return ResponseEntity.status(401).body(null);
    	}
    }	

}
