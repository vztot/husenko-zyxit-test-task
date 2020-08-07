package com.vztot.task.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    final private String appMode;

    @Autowired
    public IndexController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @GetMapping("/index")
    public String getIndex() {
        return "redirect:/";
    }

    @RequestMapping("/")
    public String getLoginPage(Model model, Authentication authentication) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", authentication.getName());
        //model.addAttribute("mode", appMode);
        return "index";
    }
}
