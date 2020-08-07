package com.vztot.task.controller;

import com.vztot.task.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/registration")
@Controller
public class RegistrationController {

    private final UserController userController;

    public RegistrationController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping
    public String getRegistrationPageWithPossibleErrorMsg(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "registration";
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        System.out.println(user);
    }
}
