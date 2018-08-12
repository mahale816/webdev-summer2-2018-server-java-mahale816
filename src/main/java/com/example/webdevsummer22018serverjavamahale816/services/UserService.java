package com.example.webdevsummer22018serverjavamahale816.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavamahale816.models.User;
import com.example.webdevsummer22018serverjavamahale816.repositories.UserRepository;



@RestController
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public User register(@RequestBody User user, HttpSession session) {
		
		User cu = userRepository.save(user);
		
		session.setAttribute("currentUser", cu);
		
		return cu;
	}
	
	@GetMapping("/profile")
	public Optional<User> profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		return userRepository.findById(currentUser.getId());
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		User fetchedUser = null;
		Iterable<User> user1 = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		for (User user2 : user1) {
			
			fetchedUser = user2;
			break;
		}
		
		if(fetchedUser != null) {
			user.setId(fetchedUser.getId());
			session.setAttribute("currentUser", user);
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		
		return fetchedUser;
	}
	
	@GetMapping("/checkLogin")
	public User checkLogin(HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
	
	@GetMapping("/api/{username}")
	public Optional<User> findUserByUsername(@PathVariable("username") String username)
	{
			return (Optional<User>) userRepository.findUserByUserName(username);
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(
			@PathVariable("userId") int id,
			@RequestBody User newUser) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastname(newUser.getLastname());
			user.setRole(newUser.getRole());
			user.setDateofbirth(newUser.getDateofbirth());
			user.setPhoneNumber(newUser.getPhoneNumber());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserByUserId(@PathVariable("userId") String userId) {
		int id = Integer.parseInt(userId);
		return userRepository.findById(id);
	}
	
	@PostMapping("/api/user")
	public void create(@RequestBody User newUser) {
		userRepository.save(newUser);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
}
