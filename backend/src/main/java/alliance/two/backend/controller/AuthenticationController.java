package alliance.two.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import alliance.two.backend.ENV;
import alliance.two.backend.api.responses.Response;
import alliance.two.backend.entity.User;
import alliance.two.backend.service.UserAuthService;


@RestController
@RequestMapping(ENV.API_v1)
public class AuthenticationController {
		
	//	TODO:
	//	1. LOGIN USER (DONE)
	//	2. FORGOT PASSWORD THEN SEND EMAIL
	//	3. RESET PASSWORD
	
	private Gson gson;
	
	@Autowired
	private UserAuthService userService;	
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {		
		
		try {		
			Optional<User> user = userService.login(email, password);			
			user.get().setPassword(null);
	        return ResponseEntity.ok(user);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());							
			Response<String> error = new Response<String>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
    }	
	
	@PostMapping(path = "/check/auth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> checkAuth(@RequestParam String email, @RequestParam String password) {		
		
		try {		
						
			Optional<User> user = userService.login(email, password);							        
	        Response<Boolean> error = new Response<Boolean>(HttpStatus.OK.value(), true);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());							
			Response<Boolean> error = new Response<Boolean>(HttpStatus.BAD_REQUEST.value(), false);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
    }	
}
