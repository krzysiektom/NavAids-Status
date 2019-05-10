package pl.coderslab.device;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.type.Type;

@Controller
@RequestMapping("device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

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
        model.addAttribute("types", deviceService.findAllTypes());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByType";
    }

    @GetMapping("/groupByOwner")
    public String allDevicesGroupByOwner(Model model) {
        model.addAttribute("owners", deviceService.findAllOwners());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByOwner";
    }

    @GetMapping("/groupByAirfield")
    public String allDevicesGroupByAirfield(Model model) {
        model.addAttribute("airfields", deviceService.findAllAirfields());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByAirfield";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = deviceService.findTypeById(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceService.findAllByType(type));
        return "devicesByType";
    }

    @GetMapping("/groupByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groups", deviceService.findAllGroups());
        model.addAttribute("deviceService", deviceService);
        return "allDevicesGroupByTypeOrderByGroup";
    }

    @GetMapping("/pivotTable")
    public String pivotTable(Model model) {
        model.addAttribute("groups", deviceService.findAllGroups());
        model.addAttribute("superiors", deviceService.findAllSuperiors());
        model.addAttribute("deviceService", deviceService);
        return "pivotTableDevicesByTypesAndOwners";
    }
}
