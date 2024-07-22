package br.com.softnunes.recrutadorapi.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {

    private String username;

    private String password;
}
