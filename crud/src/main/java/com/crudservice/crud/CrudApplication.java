package com.crudservice.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crudservice.crud.db.User;
import com.crudservice.crud.repository.UserRepository;

@SpringBootApplication
@RestController
@RequestMapping("/crud")
public class CrudApplication {
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);		
	}
		
	@PostMapping("/postuser")
	public ResponseEntity<Object> addItem(@RequestBody User user){
		try {
		userRepository.save(user);
		return ResponseEntity.ok("User was added");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error");
		}
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<Object> getItem(@PathVariable("id") long id) {
		try {
				return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
		} catch ( Exception e) {
			return ResponseEntity.badRequest().body("Error");
		}		 
	}
		
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable long id){
		try {
			userRepository.deleteById(id);
			return ResponseEntity.ok("User was deleted");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error");
		}
	}
	
	@PutMapping("/putuser/{id}")
	public ResponseEntity<Object> putItem(@PathVariable("id") long id, @RequestBody User newUser) {
		try {
			User oldUser = userRepository.findById(id).orElseThrow();
			oldUser.setFirstName(newUser.getFirstName());
			oldUser.setLastName(newUser.getLastName());
			oldUser.setAge(newUser.getAge());
			userRepository.save(oldUser);
			return ResponseEntity.ok("User was Updated");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error");			
		}
	}
	
}