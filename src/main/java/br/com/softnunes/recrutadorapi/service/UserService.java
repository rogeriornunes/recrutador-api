package br.com.softnunes.recrutadorapi.service;

import br.com.softnunes.recrutadorapi.dto.UserDTO;
import br.com.softnunes.recrutadorapi.entity.Role;
import br.com.softnunes.recrutadorapi.entity.User;
import br.com.softnunes.recrutadorapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public User saveUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new RuntimeException("Usuário já cadastrado.");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleService.saveRole(userDTO);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
