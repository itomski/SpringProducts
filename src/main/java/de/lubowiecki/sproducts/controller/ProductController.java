package de.lubowiecki.sproducts.controller;

import de.lubowiecki.sproducts.model.Category;
import de.lubowiecki.sproducts.model.Product;
import de.lubowiecki.sproducts.model.ShoppingCart;
import de.lubowiecki.sproducts.repository.CategoryRepository;
import de.lubowiecki.sproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("products")
public class ProductController {


    @Autowired // Mit Autowired können nur Componenten(Service, Repository) und Beans angebunden werden
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Beans werden in Configuration-Components vorgestellt und werden danach vom CDI-Container verwaltet
    // CDI = Context and Dependency Injection
    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Produkte");
        model.addAttribute("active", "products");
        model.addAttribute("produkte", productRepository.findAll());
        model.addAttribute("productsInCart", shoppingCart.getProducts());
        return "products";
    }

    @GetMapping("new") // product wird vom CDI-Container frisch instanziert und eingesetzt
    public String newForm(Product product, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "product-form";
    }

    @PostMapping(value = "save") // In BindingResult ist das Ergebnis der Validierung enthalten
    public String save(@Valid Product product, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "product-form";
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("buy/{id}")
    public String buy(@PathVariable Long id, Model model) {
        Optional<Product> opt = productRepository.findById(id);
        opt.ifPresent(p -> shoppingCart.buy(p)); // wenn das Produkt vorhanden ist, wird es in den ShoppingCart abgelegt
        return "redirect:/products";
    }

    @GetMapping("category/{id}")
    public String allByCategory(@PathVariable Long id, Model model) {
        Optional<Category> opt = categoryRepository.findById(id);
        opt.ifPresent(c -> {
            model.addAttribute("produkte", productRepository.findAllByCategory(c));
        });
        return "redirect:/products";
    }
}
