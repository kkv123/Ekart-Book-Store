package com.ekart.bookStore.controller.admin;

import com.ekart.bookStore.config.TokenGenerator;
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
