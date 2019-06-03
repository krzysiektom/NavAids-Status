package pl.coderslab.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("groups")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("all")
    public String allGroups(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        return "groups/allGroups";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("group", new Group());
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

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupRepository.findOne(id));
        return "groups/groupForm";
    }

    @PostMapping("/edit/{id}")
    public String editGroup(@ModelAttribute("group") @Valid Group group, BindingResult result) {
        if (result.hasErrors()) {
            return "groups/groupForm";
        }
        groupRepository.save(group);
        return "redirect:/groups/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupRepository.delete(id);
        return "redirect:/groups/all";
    }

}
