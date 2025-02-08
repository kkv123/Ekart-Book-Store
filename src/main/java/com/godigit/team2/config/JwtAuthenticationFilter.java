package com.godigit.team2.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;

// -------------------------   Here i am authorising the user with the help of token  ------------------------------------------

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "Umesh_kumar_vishwakarma-digit_insurance_123456"; // Ensure the key is at least 32 bytes long
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
                String username = claims.getSubject();
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // Handle expired token
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Token has expired");
                return;
            } catch (Exception e) {
                // Handle invalid token
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Invalid token");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}