package pl.coderslab.failure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.fix.FixRepository;

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
        model.addAttribute("allFailures", failureRepository.findAll());
        return "failures/allFailures";
    }

    @GetMapping("/{id}")
    public String failureById(@PathVariable("id") Long id, Model model) {
        Failure failure = failureRepository.findOne(id);
        model.addAttribute("failure", failure);
        model.addAttribute("allFixes", fixRepository.findAllByFailureOrderByCreatedDesc(failure));
        return "failures/failurePage";
    }
}
