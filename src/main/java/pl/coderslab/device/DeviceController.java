package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

@Controller
@RequestMapping("device")
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/")
    public String allDevices(Model model) {
        model.addAttribute("allDevices", deviceRepository.findAll());
        return "allDevices";
    }

    @GetMapping("/{id}")
    public String deviceDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("device", deviceRepository.findOne(id));
        return "devicePage";
    }

    @GetMapping("/groupByType")
    public String allDevicesGroupByType(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("deviceRepository", deviceRepository);
        return "allDevicesGroupByType";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = typeRepository.findOne(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceRepository.findAllByType(type));
        return "devicesByType";
    }

    @GetMapping("/groupByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("typeRepository", typeRepository);
        model.addAttribute("deviceRepository", deviceRepository);
        return "allDevicesGroupByTypeOrderByGroup";
    }
}
