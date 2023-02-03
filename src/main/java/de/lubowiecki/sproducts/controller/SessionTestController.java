package de.lubowiecki.sproducts.controller;

import de.lubowiecki.sproducts.repository.UserRepository;
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

    @GetMapping("login")
    public String loginForm(Model model) {
        return "login-form";
    }

    @PostMapping("login")
    public String login(String email, String password, HttpSession session, Model model) {

        Optional<User> opt = userRepository.findByEmail(email);
        if(opt.isPresent()) {

            User user = opt.get();
            if(user.getPassword().equals(password)) {
                session.setAttribute("user", user); // Alles ist ok
                // Auf seite für eingeloggte umleiten
            }
        }
        // Login nicht ik - Fehler anzeigen
        return "login-form";
    }

    @GetMapping("data")
    public String data(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null && user.getId() > 0) {
            model.addAttribute("data", "Hier sind die Daten: " + user.getId());
        }
        else {
            model.addAttribute("data", "Die Daten darfst du nicht sehen...");
        }
        return "test";
    }

    @GetMapping("logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("data", "Abgemeldet");
        return "test";
    }
}

