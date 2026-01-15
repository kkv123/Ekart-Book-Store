package com.ekart.bookStore.service.admin;

import com.ekart.bookStore.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    public ResponseEntity<String> addAdmin(RegisterDto admin);
    public Boolean checkAdmin(String username, String password);
}
