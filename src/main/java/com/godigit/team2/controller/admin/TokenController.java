package com.godigit.team2.controller.admin;

import com.godigit.team2.config.TokenGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private TokenGenerator tokenGenerator;

    @GetMapping("/token")
    public String getToken() {
        return tokenGenerator.generateToken("user");
    }
}
