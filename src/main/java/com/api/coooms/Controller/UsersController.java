package com.api.coooms.Controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.coooms.Model.JwtParameter;
import com.api.coooms.Model.Users;
import com.api.coooms.Repository.UsersRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "https://coooms.com")
public class UsersController {

	@Autowired
    UsersRepository usersRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	private JwtParameter secret = new JwtParameter();
    Algorithm algorithm = Algorithm.HMAC256(secret.getSecretKey());
	
	
//	@GetMapping("/users")
//	public List<Users> getUsers() {
//		List<Users> users = usersRepository.findAll();
//		return users;
//	}
	
//	@GetMapping(path = "/user", params = "email")
//	public Users getUserByEmail(@RequestParam("email") String email) {
//		Users user = usersRepository.findByEmail(email);
//		return user;
//	}
	
	@GetMapping(path = "/user")
	public Users getUserByJWT(HttpServletRequest request) {
        String xAuthToken = request.getHeader("X-AUTH-TOKEN");
        
        DecodedJWT decodedJWT = JWT.decode(xAuthToken.substring(7));
        
        List<String> userEmailList = decodedJWT.getAudience();
        String email = userEmailList.get(0);
        
        Users user = usersRepository.findByEmail(email);
		return user;
	}
	
	@PostMapping("/user")
	public void save(@RequestBody Users users) {
		String password = users.getPassword();
		String digest = passwordEncoder.encode(password);
		users.setPassword(digest);
		this.usersRepository.save(users);
	}

	@PutMapping("/user")
	public ResponseEntity<String> update(HttpServletRequest request, @RequestBody Users users) {
		String xAuthToken = request.getHeader("X-AUTH-TOKEN");
        
        DecodedJWT decodedJWT = JWT.decode(xAuthToken.substring(7));
        
        List<String> userEmailList = decodedJWT.getAudience();
        String email = userEmailList.get(0);
        
        if(users.getEmail().equals(email)) {
        	Users user = usersRepository.findByEmail(email);
        	users.setId(user.getId());
        	this.usersRepository.save(users);
        	return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	

	@DeleteMapping(path = "/user", params = "email")
	public ResponseEntity<String> delete(HttpServletRequest request, @RequestParam("email") String emailPram) {
		String xAuthToken = request.getHeader("X-AUTH-TOKEN");
        
        DecodedJWT decodedJWT = JWT.decode(xAuthToken.substring(7));
        
        List<String> userEmailList = decodedJWT.getAudience();
        String email = userEmailList.get(0);
        
        if(emailPram.equals(email)) {
        	this.usersRepository.deleteByEmail(emailPram);
        	return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	
	}
}
