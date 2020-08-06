package com.vztot.task.service;

import com.vztot.task.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder
                = org.springframework.security.core.userdetails.User.withUsername(login);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles()
                .stream()
                .map(role -> role.getRole())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
