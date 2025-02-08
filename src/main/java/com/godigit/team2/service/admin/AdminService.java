package com.godigit.team2.service.admin;

import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.entity.admin.Admin;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    public ResponseEntity<String> addAdmin(RegisterDto admin);
    public Boolean checkAdmin(String username, String password);
}
