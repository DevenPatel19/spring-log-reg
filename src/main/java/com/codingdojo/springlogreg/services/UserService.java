package com.codingdojo.springlogreg.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.springlogreg.models.LoginUser;
import com.codingdojo.springlogreg.models.User;
import com.codingdojo.springlogreg.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
// TO-DO - Reject values or register if no errors:
        // Reject if email is taken (present in database)
		//1. find user in the DB by email
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		//2. if the email is present, reject
		if(optionalUser.isPresent()){
			result.rejectValue("email", "Unique", "Email is already registered");
		}
        // Reject if password doesn't match confirmation
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("email", "Matches", "Passwords do not match!");
		}
        // if result has errors, return
		if(result.hasErrors()) {
			// Return null if result has errors
			return null;
		} 
        // Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
 // TO-DO - Reject values:
    	// 1. Find user in the DB by email
		Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
		// 2. if the email is not present, reject
		if(optionalUser.isEmpty()){ 
			result.rejectValue("email", "Unique", "Email is not registered");
			return null;
		}
		// 3.1 grab the user from potential user
		User user = optionalUser.get();
		// 3.2 if BCrypt password match fails
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password!");
		}
		// 4. if result has errors, return
		if(result.hasErrors()) {
			// Return null if result has errors
			return null;
		}
        // Otherwise, return the user object
		return user;
		
	}
	
}
