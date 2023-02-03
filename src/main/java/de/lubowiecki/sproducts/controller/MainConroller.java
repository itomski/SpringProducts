package de.lubowiecki.sproducts.controller;

import de.lubowiecki.sproducts.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class MainConroller {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Startseite");
        model.addAttribute("active", "home");
        return "standard";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        model.addAttribute("title", "Kontakt");
        model.addAttribute("active", "contact");
        return "standard";
    }
}
