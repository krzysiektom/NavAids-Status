package pl.coderslab.fix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;
import pl.coderslab.failure.FailureRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("fixes")
public class FixController {
    @Autowired
    private FixRepository fixRepository;
    @Autowired
    private FixService fixService;

    @GetMapping("/{id}")
    public String fixById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("fix", fixRepository.findOne(id));
        return "fixes/fixPage";
    }

    @GetMapping("/add/{deviceId}")
    public String showForm(@PathVariable("deviceId") Long deviceId, Model model) {
        Fix fix = new Fix();
        model.addAttribute("fix", fix);
        return "fixes/fixForm";
    }

    @PostMapping("/add/{deviceId}")
    public String addFix(@PathVariable("deviceId") Long deviceId, @ModelAttribute("fix") @Valid Fix fix, BindingResult result, @RequestParam(name = "isRepaired", defaultValue="0") boolean isRepaired ) {
        if (result.hasErrors()) {
            return "fixes/fixForm";
        }
        fixService.save(fix,deviceId,isRepaired);
        if(isRepaired){
            return "redirect:/devices/"+deviceId;
        }

        return "redirect:/failures/" + deviceId;
    }


}
