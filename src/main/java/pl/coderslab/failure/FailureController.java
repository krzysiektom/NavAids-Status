package pl.coderslab.failure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("failure")
public class FailureController {
    @Autowired
    FailureService failureService;

    @GetMapping("/")
    public String allFailures(Model model) {
        model.addAttribute("allFailures", failureService.findAll());
        return "failure/allFailures";
    }

    @GetMapping("/{id}")
    public String failureById(@PathVariable("id") Long id, Model model) {
        Failure failure = failureService.findOne(id);
        model.addAttribute("failure", failure);
        model.addAttribute("allFixes", failureService.findAllByFailure(failure));
        return "failure/failurePage";
    }
}
