package com.godigit.team2.service.user_register;

import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.entity.user.User;
import org.springframework.http.ResponseEntity;

public interface UserRegisterService {
    public ResponseEntity<String> registerUser(RegisterDto user);
    public User loginUser(String username, String password);
}
