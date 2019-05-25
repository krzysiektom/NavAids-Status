package pl.coderslab.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("types")
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/all")
    public String allTypes(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        return "types/allTypes";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("type", new Type());
        model.addAttribute("groups", groupRepository.findAll());
        return "types/typeForm";
    }

    @PostMapping("/add")
    public String addType(@ModelAttribute("type") @Valid Type type, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("groups", groupRepository.findAll());
            return "types/typeForm";
        }
        typeRepository.save(type);
        return "redirect:/types/all";
    }

    @GetMapping("/all/{groupId}")
    public String typesInGroup(@PathVariable("groupId") Long groupId, Model model) {
        Group group = groupRepository.findOne(groupId);
        model.addAttribute("group", group);
        model.addAttribute("types", typeRepository.findAllByGroup(group));
        return "types/typesByGroup";
    }

}
