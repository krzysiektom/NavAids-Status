package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

@Controller
@RequestMapping("device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    AirfieldRepository airfieldRepository;

    @GetMapping("/")
    public String allDevices(Model model) {
        model.addAttribute("allDevices", deviceService.findAll());
        return "allDevices";
    }

    @GetMapping("/{id}")
    public String deviceDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("device", deviceService.findById(id));
        return "devicePage";
    }

    @GetMapping("/groupByType")
    public String allDevicesGroupByType(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByType";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = typeRepository.findOne(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceService.findAllByType(type));
        return "devicesByType";
    }

    @GetMapping("/groupByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByTypeOrderByGroup";
    }

    @GetMapping("/pivotTable")
    public String pivotTable(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("superiors", ownerRepository.findAllBySuperior(ownerRepository.findOne(1L)));
        model.addAttribute("deviceService", deviceService);
        return "pivotTableDevicesByTypesAndOwners";
    }
}
