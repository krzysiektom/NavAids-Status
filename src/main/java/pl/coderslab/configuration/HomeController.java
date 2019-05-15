package pl.coderslab.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.AuthHandler;
import pl.coderslab.airfield.AirfieldService;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerService;

@Controller
@SessionAttributes({"AllAirfields","AllOwners"})
public class HomeController {
    @Autowired
    AirfieldService airfieldService;

    @Autowired
    OwnerService ownerService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldService.findAll());
        model.addAttribute("AllOwners", ownerService.findAllBySuperior());
        return "index";
    }
}
