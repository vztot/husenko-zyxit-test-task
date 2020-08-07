package com.vztot.task.config;

import com.vztot.task.entity.Role;
import com.vztot.task.entity.User;
import com.vztot.task.repository.RoleRepository;
import com.vztot.task.service.UserService;
import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private RoleRepository roleRepository;
    private UserService userService;

    public DataInjector(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        injectRoles();
        injectAdmin();
    }

    private void injectRoles() {
        if (roleRepository.findByRole("ADMIN") == null) {
            Role adminRole = new Role(null, "ADMIN");
            Role userRole = new Role(null, "USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }

    private void injectAdmin() {
        if (userService.findUserByLogin("admin") == null) {
            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");
            User user = new User(null, "user", "1234",
                    new HashSet<>(Arrays.asList(userRole)));
            User admin = new User(null, "admin", "1234",
                    new HashSet<>(Arrays.asList(userRole, adminRole)));
            userService.saveUser(user);
            userService.saveUser(admin);
        }
    }
}
