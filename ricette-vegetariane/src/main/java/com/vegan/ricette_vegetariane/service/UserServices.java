package com.vegan.ricette_vegetariane.service;

import com.vegan.ricette_vegetariane.dto.UserDTO;
import com.vegan.ricette_vegetariane.entity.Users;
import com.vegan.ricette_vegetariane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo per caricare un utente per nome utente
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    // Metodo per trovare un utente per username
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Metodo per trovare un utente per email
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Metodo per salvare un utente
    public Users saveUser(UserDTO userDTO) {
        Users user = convertToEntity(userDTO); // Converte DTO in entità User
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codifica la password
        return userRepository.save(user);
    }

    // Metodo per registrare un nuovo utente
    public Users registerNewUser(UserDTO userDTO) {
        // Verifica se l'username è già utilizzato
        if (existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username is already taken: " + userDTO.getUsername());
        }

        // Verifica se l'email è già utilizzata
        if (existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use: " + userDTO.getEmail());
        }

        // Crea e salva l'utente
        Users user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codifica la password
        return userRepository.save(user);
    }

    // Metodo per verificare l'esistenza di un utente per username
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Metodo per verificare l'esistenza di un utente per email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Metodo per trovare un utente per ID
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    // Metodo per trovare tutti gli utenti
    public List<UserDTO> findAll() {
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO) // Converte entità User in DTO
                .collect(Collectors.toList());
    }

    // Metodo privato per la conversione da UserDTO a entità User
    private Users convertToEntity(UserDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        // Altri campi dell'entità User, se necessario

        return user;
    }

    // Metodo privato per la conversione da User a UserDTO
    private UserDTO convertToDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        // Altri campi del DTO, se necessario

        return userDTO;
    }
}
