package pl.coderslab.configuration;

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
    private final AirfieldRepository airfieldRepository;
    private final OwnerService ownerService;
    private final AuthHandler authHandler;
    private final UserRepository userRepository;

    public HomeController(AirfieldRepository airfieldRepository, OwnerService ownerService, AuthHandler authHandler, UserRepository userRepository) {
        this.airfieldRepository = airfieldRepository;
        this.ownerService = ownerService;
        this.authHandler = authHandler;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldRepository.findAll());
        model.addAttribute("AllOwners", ownerService.findAllBySuperior());

        //mock login
        authHandler.setUser(userRepository.findOne(1L));
        authHandler.setLogged(true);

        return "index";
    }
}
