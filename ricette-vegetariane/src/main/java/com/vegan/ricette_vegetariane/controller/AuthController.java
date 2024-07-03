package com.vegan.ricette_vegetariane.controller;

import com.vegan.ricette_vegetariane.dto.UserDTO;
import com.vegan.ricette_vegetariane.security.JwtUtils;
import com.vegan.ricette_vegetariane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO userDTO) {
        // Autenticazione dell'utente tramite Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );

        // Imposta l'oggetto Authentication nel contesto di sicurezza
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Genera il token JWT
        String jwt = jwtUtils.generateToken(authentication);

        // Restituisce la risposta con il token JWT
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Registra un nuovo utente
        userService.registerNewUser(userDTO);

        // Risposta di conferma
        return ResponseEntity.ok("User registered successfully");
    }
}
