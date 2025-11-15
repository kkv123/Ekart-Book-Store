package com.godigit.team2.controller.userregister;


import com.godigit.team2.config.TokenGenerator;
import com.godigit.team2.dto.RegisterDto;
import com.godigit.team2.service.user_register.UserRegisterServiceImp;
import com.godigit.team2.entity.user.User;
import org.antlr.v4.runtime.TokenFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.godigit.team2.AuditLog.AuditDto.fetchAgentDetails;

@RestController
@RequestMapping("/e-kart/user")
public class UserRegistrationController {

    private TokenGenerator tokenGenerator;
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
    public ResponseEntity<String> LoginUser(@RequestBody User user) {
        User loggedInUser = userRegisterServiceImp.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            JSONObject token = new JSONObject();
            token.put("token",tokenGenerator.generateToken(user.getRole()));
            token.put("user",loggedInUser.getUsername());
            token.put("role",loggedInUser.getRole());
            return new ResponseEntity<>(token.toString(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllUser(){
        return userRegisterServiceImp.getAllUsers();
    }


}
