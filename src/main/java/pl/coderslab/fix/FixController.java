package pl.coderslab.fix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;
import pl.coderslab.failure.Failure;
import pl.coderslab.failure.FailureRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("fixes")
public class FixController {
    @Autowired
    private FixRepository fixRepository;
    @Autowired
    private FailureRepository failureRepository;
    @Autowired
    private AuthHandler authHandler;

    @GetMapping("/{id}")
    public String fixById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("fix", fixRepository.findOne(id));
        return "fixes/fixPage";
    }

    @GetMapping("/add/{id}")
    public String showForm(@PathVariable("id") Long id, Model model) {
        Fix fix = new Fix();
        System.out.println(fix);
        model.addAttribute("fix", fix);
        return "fixes/fixForm";
    }

    @PostMapping("/add/{id}")
    public String addFix(@ModelAttribute("fix") @Valid Fix fix, @PathVariable("id") Long id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "fixes/fixForm";
        }
        fix.setId(null);
        fix.setFailure(failureRepository.findOne(id));
        fix.setUser(authHandler.getUser());
        fixRepository.save(fix);
        return "redirect:/failures/" + id;
    }


}
