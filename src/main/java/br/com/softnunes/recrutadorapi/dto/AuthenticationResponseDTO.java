package br.com.softnunes.recrutadorapi.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    private boolean success;
    private String message;
    private String token;
    private String username;
    private String role;

    public AuthenticationResponseDTO(boolean success, String message, String token, String username, String role) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.username = username;
        this.role = role;
    }
}
