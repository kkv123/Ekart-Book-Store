package com.godigit.team2.AuditLog;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuditDto {

    public static ResponseEntity<String> fetchAgentDetails(String authorization) {
        try {
            String[] parts = authorization.split("\\.");
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]),
                    StandardCharsets.UTF_8);
            payload = payload.replaceFirst("scope", "scopeDuplicate");
            JSONObject payloadJson = new JSONObject(payload);
            String tokenUserId = payloadJson.optString("role");
            return new ResponseEntity<>(tokenUserId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception During fetching user from token", HttpStatus.OK);
        }
    }
}
