package com.ekart.bookStore.service.admin;

import com.ekart.bookStore.dto.RegisterDto;
import com.ekart.bookStore.entity.admin.Admin;
import com.ekart.bookStore.repository.admin.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {


    AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public ResponseEntity<String> addAdmin(RegisterDto admin) {
        Admin admin1=adminRepo.findByUsername(admin.getUsername());
        Admin admin2=new Admin();
        if(admin1!=null) {
            return ResponseEntity.badRequest().body("Admin already exists");
        }
        String userId;
        while(true){
            userId = generateEpochBasedUUID();
            if(adminRepo.findByUser_id(userId)==null){
                break;
            }
        }
        admin2.setAdmin_id(userId);
        admin2.setUsername(admin.getUsername());
        admin2.setEmail(admin.getEmail());
        admin2.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin2.setRole("ADMIN");
        adminRepo.save(admin2);
        return ResponseEntity.ok("Admin added successfully");
    }

    @Override
    public Boolean checkAdmin(String username, String password) {
        return adminRepo.findAll().stream()
                .anyMatch(a -> a.getUsername().equals(username) && a.getPassword().equals(password));
    }

    public String generateEpochBasedUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 12);
    }


}
