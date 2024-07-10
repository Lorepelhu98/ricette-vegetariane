package com.vegan.ricette_vegetariane.service;

import com.vegan.ricette_vegetariane.dto.UserDTO;
import com.vegan.ricette_vegetariane.entity.User;
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
public class UserService {

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
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Metodo per trovare un utente per email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Metodo per salvare un utente
    public User saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO); // Converte DTO in entità User
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Codifica la password
        return userRepository.save(user);
    }

    // Metodo per registrare un nuovo utente
    public User registerNewUser(UserDTO userDTO) {
        // Verifica se l'username è già utilizzato
        if (existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username is already taken: " + userDTO.getUsername());
        }

        // Verifica se l'email è già utilizzata
        if (existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email is already in use: " + userDTO.getEmail());
        }

        // Crea e salva l'utente
        User user = convertToEntity(userDTO);
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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Metodo per trovare tutti gli utenti
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO) // Converte entità User in DTO
                .collect(Collectors.toList());
    }

    // Metodo privato per la conversione da UserDTO a entità User
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        // Altri campi dell'entità User, se necessario

        return user;
    }

    // Metodo privato per la conversione da User a UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        // Altri campi del DTO, se necessario

        return userDTO;
    }
}
