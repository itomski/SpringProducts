package de.lubowiecki.sproducts.controller;

import de.lubowiecki.sproducts.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("products")
public class ProductController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Produkte");
        model.addAttribute("active", "products");

        Set<Product> produkte = Set.of(new Product("Handschuhe", 19.99),
                                       new Product("Mütze", 12.99),
                                       new Product("Hammer", 22.99));

        model.addAttribute("produkte", produkte);

        return "standard";
    }
}
