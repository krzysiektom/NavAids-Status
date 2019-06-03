package pl.coderslab.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/edit/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerRepository.findOne(id));
        model.addAttribute("superiors", ownerRepository.findAll());
        return "owners/ownerForm";
    }

    @PostMapping("/edit/{id}")
    public String editOwner(@ModelAttribute("owner") @Valid Owner owner, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("superiors", ownerRepository.findAll());
            return "owners/ownerForm";
        }
        ownerRepository.save(owner);
        return "redirect:/owners/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable Long id) {
        ownerRepository.delete(id);
        return "redirect:/owners/all";
    }
}
