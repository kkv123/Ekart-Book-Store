package com.godigit.team2.controller.userregister;


import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.service.user_register.UserRegisterServiceImp;
import com.godigit.team2.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.godigit.team2.AuditLog.AuditDto.fetchAgentDetails;

@RestController
@RequestMapping("/e-kart/user")
public class UserRegistrationController {


    private UserRegisterServiceImp userRegisterServiceImp;

    @Autowired
    public UserRegistrationController(UserRegisterServiceImp userRegisterServiceImp) {
        this.userRegisterServiceImp = userRegisterServiceImp;
    }


    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody RegisterDto user, @RequestHeader String authorization) {
        ResponseEntity<String> response=userRegisterServiceImp.registerUser(user);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody User user) {
        User loggedInUser = userRegisterServiceImp.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
