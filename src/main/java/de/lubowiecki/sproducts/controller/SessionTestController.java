package de.lubowiecki.sproducts.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import de.lubowiecki.sproducts.model.User;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("sess")
public class SessionTestController {

    public User createUser() {
        return new User();
    }

    @GetMapping("login")
    public String login(Model model, HttpSession session) {
        // Formdaten prüfen und wenn User vorhanden und Zugangsdaten ok, dann id in userId schreiben
        User user = new User();
        user.setId(10);
        session.setAttribute("user", user);
        model.addAttribute("data", "Angemeldet");
        return "test";
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
        session.setAttribute("user", null);
        model.addAttribute("data", "Abgemeldet");
        return "test";
    }

}
