package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.airfield.AirfieldService;
import pl.coderslab.group.GroupService;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeService;

@Controller
@RequestMapping("device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private AirfieldService airfieldService;
    @Autowired
    private GroupService groupService;

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
        model.addAttribute("groupByTypes", deviceService.groupByTypes());
        return "allDevicesGroupByType";
    }

    @GetMapping("/groupByOwner")
    public String allDevicesGroupByOwner(Model model) {
        model.addAttribute("groupByOwners", deviceService.groupByOwner());
        return "allDevicesGroupByOwner";
    }

    @GetMapping("/groupByAirfield")
    public String allDevicesGroupByAirfield(Model model) {
        model.addAttribute("groupByAirfields", deviceService.groupByAirfield());
        return "allDevicesGroupByAirfield";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = typeService.findById(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceService.findAllByType(type));
        return "devicesByType";
    }

    @GetMapping("/groupByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groupByGroups", deviceService.groupByGroups());
        return "allDevicesGroupByTypeOrderByGroup";
    }

    @GetMapping("/pivotTable")
    public String pivotTable(Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("superiors", ownerService.findAllSuperiors());
        model.addAttribute("deviceService", deviceService);
        return "pivotTableDevicesByTypesAndOwners";
    }
}
