package alliance.two.backend.Impl.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alliance.two.backend.AES_Encryption.EncryptDecrypt;
import alliance.two.backend.entity.User;
import alliance.two.backend.repository.UserRepository;
import alliance.two.backend.service.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	private EncryptDecrypt aes;
	
    @Autowired
    private UserRepository userRepository; 


	public Optional<User> login(String email, String password) {
		
		Optional<User> user = userRepository.findByEmail(email);		
		
		try {													
			
			if(user.isPresent()) {	
			
				aes = new EncryptDecrypt();		
				String encryptedPassword = aes.encrypt(password); 				
				
				if (user.get().getPassword().equals(encryptedPassword)) {
					return Optional.of(user.get());	
				}
				else {							
					throw new RuntimeException("Password does not match");
				}				
			}
			else {				
				throw new RuntimeException("Email does not exist");
			}
		} 
		catch (Exception e) {
			
			System.err.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}					
	}
}
