package de.lubowiecki.sproducts.controller;

import de.lubowiecki.sproducts.repository.UserRepository;
import de.lubowiecki.sproducts.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import de.lubowiecki.sproducts.model.User;

import java.util.Optional;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("sess")
public class SessionTestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String loginForm(Model model) {
        if(loginService.isLoggedIn()) // Wenn eingeloggt, dann redirect zu den Geheimdaten
            return "redirect:/sess/data";

        return "login-form";
    }

    @PostMapping("login")
    public String login(String email, String password, Model model) {

        Optional<User> opt = userRepository.findByEmail(email);
        if(opt.isPresent()) {
            User user = opt.get();
            if(user.getPassword().equals(password)) {
                loginService.doLogIn(user);
                return "redirect:/sess/data";
            }
        }
        model.addAttribute("error", true);
        return "login-form";
    }

    @GetMapping("data")
    public String data(Model model) {
        if(loginService.isLoggedIn()) {
            model.addAttribute("data", "Hier sind die Daten: " + loginService.getUser().getId());
        }
        else {
            model.addAttribute("data", "Die Daten darfst du nicht sehen...");
        }
        return "test";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        loginService.doLogOut();
        model.addAttribute("data", "Abgemeldet");
        return "test";
    }
}

