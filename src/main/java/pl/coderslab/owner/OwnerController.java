package pl.coderslab.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("owners")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/all")
    public String allOwners(Model model) {
        model.addAttribute("owners", ownerRepository.findAll());
        return "owners/allOwners";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("owner", new Owner());
        model.addAttribute("superiors", ownerRepository.findAll());
        return "owners/ownerForm";
    }

    @PostMapping("/add")
    public String addOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("superiors", ownerRepository.findAll());
            return "owners/ownerForm";
        }
        ownerRepository.save(owner);
        return "redirect:/owners/all";
    }

}
