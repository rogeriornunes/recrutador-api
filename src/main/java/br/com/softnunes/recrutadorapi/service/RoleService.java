package br.com.softnunes.recrutadorapi.service;

import br.com.softnunes.recrutadorapi.dto.UserDTO;
import br.com.softnunes.recrutadorapi.entity.Role;
import br.com.softnunes.recrutadorapi.entity.User;
import br.com.softnunes.recrutadorapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
    }

    public Role saveRole(UserDTO userDTO) {
        Role role = roleRepository.findByName("ROLE_" + userDTO.getRole().toUpperCase());
        if (role == null) {
            role = new Role();
            role.setName("ROLE_" + userDTO.getRole().toUpperCase());
            roleRepository.save(role);
        }
        return role;
    }
}
