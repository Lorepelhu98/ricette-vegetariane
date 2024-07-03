package com.vegan.ricette_vegetariane.controller;

import com.vegan.ricette_vegetariane.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/share")
public class SharingController {

    private final JwtUtils jwtUtils;

    @Autowired
    public SharingController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/generateLink")
    @PreAuthorize("isAuthenticated()")
    public String generateShareableLink() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtUtils.generateToken(authentication);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/share/access")
                .queryParam("token", token)
                .build()
                .toUri();
        return uri.toString();
    }

    @GetMapping("/access")
    public String accessSharedContent(@RequestParam String token) {
        if (jwtUtils.isTokenValid(token)) {
            String username = jwtUtils.getUsernameFromToken(token);
            // Fetch and return the shared content based on username or other information in the token
            return "Content for user: " + username;
        } else {
            return "Invalid or expired token";
        }
    }
}
