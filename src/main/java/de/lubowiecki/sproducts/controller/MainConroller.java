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
        //model.addAttribute("title", "Startseite");
        model.addAttribute("headline", "Herzlich Willkommen");
        model.addAttribute("active", "home");

        List<String> namen = List.of("Peter", "Carol", "Bruce", "Steve", "Natasha");
        model.addAttribute("namen", namen);


        Set<Product> produkte = Set.of(new Product("Handschuhe", 19.99),
                                       new Product("Mütze", 12.99),
                                       new Product("Hammer", 22.99));

        model.addAttribute("produkte", produkte);

        return "standard";
    }
}
