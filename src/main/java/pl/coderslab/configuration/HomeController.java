package pl.coderslab.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.AuthHandler;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.user.UserRepository;

@Controller
@SessionAttributes({"AllAirfields", "AllOwners"})
public class HomeController {
    @Autowired
    AirfieldRepository airfieldRepository;

    @Autowired
    OwnerService ownerService;
    @Autowired
    AuthHandler authHandler;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldRepository.findAll());
        model.addAttribute("AllOwners", ownerService.findAllBySuperior());
        authHandler.setUser(userRepository.findOne(1L));
        authHandler.setLogged(true);
        return "index";
    }
}
