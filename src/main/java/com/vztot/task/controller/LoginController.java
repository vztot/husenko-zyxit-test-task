package com.vztot.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping
    public String getLoginPageWithError(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "");
        }
        return "login";
    }
}
