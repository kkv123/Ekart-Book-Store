package com.ekart.bookStore.service.user_register;

import com.ekart.bookStore.dto.RegisterDto;
import com.ekart.bookStore.entity.user.User;
import org.springframework.http.ResponseEntity;

public interface UserRegisterService {
          ResponseEntity<String> registerUser(RegisterDto user);
          User loginUser(String username, String password);
          ResponseEntity<String> getAllUsers();
}
