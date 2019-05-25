package pl.coderslab.airfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.owner.OwnerRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("airfields")
public class AirfieldController {
    @Autowired
    private AirfieldRepository airfieldRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/all")
    public String allAirfields(Model model) {
        model.addAttribute("airfields", airfieldRepository.findAll());
        return "airfields/allAirfields";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("airfield", new Airfield());
        model.addAttribute("owners", ownerRepository.findAll());
        return "airfields/airfieldForm";
    }

    @PostMapping("/add")
    public String addAirfield(@ModelAttribute("airfield") @Valid Airfield airfield, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("owners", ownerRepository.findAll());
            return "airfields/airfieldForm";
        }
        airfieldRepository.save(airfield);
        return "redirect:/airfields/all";
    }

}
