package br.com.softnunes.recrutadorapi.service;

import br.com.softnunes.recrutadorapi.dto.UserDTO;
import br.com.softnunes.recrutadorapi.entity.Role;
import br.com.softnunes.recrutadorapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

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
