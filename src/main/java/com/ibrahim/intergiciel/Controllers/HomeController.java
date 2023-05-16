package com.ibrahim.intergiciel.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class HomeController {
    @GetMapping({"/", "/home"})
    public String Home(@RequestParam(value = "name", defaultValue = "D'Intergiciel", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "home" ;
    }
}
