package com.vztot.task.controller;

import com.vztot.task.entity.Role;
import com.vztot.task.entity.User;
import com.vztot.task.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAll(Model model) {
        List<User> list = userService.findAll().stream()
                .filter(user -> {
                    Set<Role> set = user.getRoles();
                    return set.stream()
                            .filter(role -> role.getRole().equals("ADMIN"))
                            .collect(Collectors.toList())
                            .size() > 0;
                })
                .collect(Collectors.toList());
        model.addAttribute("list", list);
        return "admin";
    }
}
