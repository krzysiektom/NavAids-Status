package pl.coderslab.group;

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
@RequestMapping("groups")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/groupForm";
    }

    @PostMapping("/add")
    public String addGroup(@ModelAttribute("group") @Valid Group group, BindingResult result) {
        if (result.hasErrors()) {
            return "groups/groupForm";
        }
        groupRepository.save(group);
        return "redirect:/groups/all";
    }

    @GetMapping("all")
    public String allGroups(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        return "groups/allGroups";
    }

}
