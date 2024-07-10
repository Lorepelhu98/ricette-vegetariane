package com.vegan.ricette_vegetariane.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vegan.ricette_vegetariane.entity.Users;
import com.vegan.ricette_vegetariane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{username}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable String username,
                                               @RequestParam("file") MultipartFile file) {
        try {
            // Carica l'immagine su Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("public_id", username + "_avatar"));

            // Recupera l'URL dell'immagine
            String url = (String) uploadResult.get("url");

            // Aggiorna l'utente con l'URL dell'immagine avatar
            Optional<Users> userOptional = userRepository.findOneByUsername(username);
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                user.setAvatar(url);
                userRepository.save(user);
                return ResponseEntity.ok(url);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload avatar: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload avatar: " + e.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}/avatar")
    public ResponseEntity<String> getUserAvatar(@PathVariable String username) {
        return userRepository.findOneByUsername(username)
                .map(user -> ResponseEntity.ok(user.getAvatar()))
                .orElse(ResponseEntity.notFound().build());
    }
}
