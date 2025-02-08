package com.godigit.team2.service.user_register;

import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.entity.user.User;
import com.godigit.team2.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegisterServiceImp implements UserRegisterService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(RegisterDto user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        User newUser = new User();
        if(existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        String userId;
        while(true){
            userId = generateEpochBasedUUID();
            if(userRepository.findByUser_id(userId)==null){
                break;
            }
        }
        newUser.setUser_id(userId);
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return  ResponseEntity.ok("User registered successfully");
    }


    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null &&  passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    public String generateEpochBasedUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString().replace("-", "").substring(0, 12);
    }
}