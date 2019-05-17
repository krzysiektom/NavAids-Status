package pl.coderslab.fix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.failure.Failure;

@Controller
@RequestMapping("fixes")
public class FixController {
    @Autowired
    private FixRepository fixRepository;

    @GetMapping("/{id}")
    public String fixById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("fix", fixRepository.findOne(id));
        return "fixes/fixPage";
    }
}
