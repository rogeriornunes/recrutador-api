package br.com.softnunes.recrutadorapi.controller;

import br.com.softnunes.recrutadorapi.auth.JwtUtil;
import br.com.softnunes.recrutadorapi.dto.AuthenticationDTO;
import br.com.softnunes.recrutadorapi.dto.AuthenticationResponseDTO;
import br.com.softnunes.recrutadorapi.dto.UserDTO;
import br.com.softnunes.recrutadorapi.exception.ApiResponse;
import br.com.softnunes.recrutadorapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Usuário e/ou senha inválidos."));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        final String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        AuthenticationResponseDTO response = new AuthenticationResponseDTO(true, "Autenticação bem-sucedida.", jwt, userDetails.getUsername(), role);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Usuário cadastrado com sucesso."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage()));
        }
    }
}
