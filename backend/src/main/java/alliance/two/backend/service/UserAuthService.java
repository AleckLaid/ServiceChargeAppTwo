package alliance.two.backend.service;

import java.util.Optional;

import alliance.two.backend.entity.User;

public interface UserAuthService {
	
    Optional<User> login(String email, String password);
    
}
