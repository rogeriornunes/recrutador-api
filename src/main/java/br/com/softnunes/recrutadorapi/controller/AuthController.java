package br.com.softnunes.recrutadorapi.controller;

import br.com.softnunes.recrutadorapi.dto.UserDTO;
import br.com.softnunes.recrutadorapi.entity.User;
import br.com.softnunes.recrutadorapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        User user = userService.findByUsername(userDTO.getUsername());
        if (user != null && new BCryptPasswordEncoder().matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login bem-sucedido!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" Usuário e/ou senha inválidos.");
    }
}
