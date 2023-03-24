package alliance.two.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alliance.two.backend.ENV;

@RestController
@RequestMapping(ENV.API_v1)
public class UserController {
	
//	TODO:
//	1. REGISTER USER
//	2. UPDATE USER
	
	@GetMapping("/user")
	public String test() {
	    return "user";
	}		
}
