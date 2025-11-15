package com.godigit.team2.service.user_register;

import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.entity.user.User;
import org.springframework.http.ResponseEntity;

public interface UserRegisterService {
          ResponseEntity<String> registerUser(RegisterDto user);
          User loginUser(String username, String password);
          ResponseEntity<String> getAllUsers();
}
