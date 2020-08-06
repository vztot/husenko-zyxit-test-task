package com.vztot.task.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private String appMode;

    @Autowired
    public IndexController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/index")
    public String getLoginPage(Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Ömerrrr");
        model.addAttribute("mode", appMode);
        return "index";
    }
}
