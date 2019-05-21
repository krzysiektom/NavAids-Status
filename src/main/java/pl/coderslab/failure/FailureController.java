package pl.coderslab.failure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.fix.FixRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("failures")
public class FailureController {
    @Autowired
    FailureRepository failureRepository;
    @Autowired
    FixRepository fixRepository;
    @Autowired
    FailureService failureService;


    @GetMapping("/")
    public String allFailures(Model model) {
        model.addAttribute("allFailures", failureRepository.findAllByIsFixedIsFalse());
        return "failures/allFailures";
    }

    @GetMapping("/{id}")
    public String failureById(@PathVariable("id") Long id, Model model) {
        Failure failure = failureRepository.findOne(id);
        model.addAttribute("failure", failure);
        model.addAttribute("allFixes", fixRepository.findAllByFailureOrderByCreatedDesc(failure));
        return "failures/failurePage";
    }

    @GetMapping("/add/{deviceId}")
    public String addForm(Model model) {
        FailureAndFix failureAndFix = new FailureAndFix();
        model.addAttribute("failureAndFix", failureAndFix);
        return "failures/failureForm";
    }

    @PostMapping("/add/{deviceId}")
    public String addFailure(@PathVariable("deviceId") Long deviceId, @ModelAttribute("failureAndFix") @Valid FailureAndFix failureAndFix, BindingResult result) {
        if (result.hasErrors()) {
            return "failures/failureForm";
        }
        Long failureId = failureService.save(failureAndFix, deviceId);
        return "redirect:/failures/" + failureId;
    }
}
