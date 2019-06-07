package pl.coderslab.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.owner.OwnerService;

@Controller
@SessionAttributes({"AllAirfields", "AllOwners"})
public class HomeController {
    private final AirfieldRepository airfieldRepository;
    private final OwnerService ownerService;

    public HomeController(AirfieldRepository airfieldRepository, OwnerService ownerService) {
        this.airfieldRepository = airfieldRepository;
        this.ownerService = ownerService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldRepository.findAll());
        model.addAttribute("AllOwners", ownerService.findAllBySuperior());
        return "index";
    }
}
