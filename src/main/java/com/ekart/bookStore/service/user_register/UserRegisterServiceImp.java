package com.ekart.bookStore.service.user_register;

import com.ekart.bookStore.dto.RegisterDto;
import com.ekart.bookStore.entity.user.User;
import com.ekart.bookStore.repository.user.UserRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ResponseEntity<String> getAllUsers() {
        List<User> users = userRepository.findAll();
        JSONArray sanitizedUsers = new JSONArray();

        for (User user : users) {
            JSONObject userJson = new JSONObject(user);
            userJson.remove("password");
            sanitizedUsers.put(userJson);
        }

        return new ResponseEntity<>(sanitizedUsers.toString(), HttpStatus.OK);
    }
}