package pl.coderslab.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.AuthHandler;
import pl.coderslab.airfield.AirfieldService;

@Controller
public class HomeController {
    @Autowired
    AirfieldService airfieldService;
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldService.findAll());
        return "index";
    }
}
