package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldService;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupService;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeService;

@Controller
@RequestMapping("devices")
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
        return "devices/allDevices";
    }

    @GetMapping("/{id}")
    public String deviceDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("device", deviceService.findById(id));
        return "devices/devicePage";
    }

    @GetMapping("/groupByType")
    public String allDevicesGroupByType(Model model) {
        model.addAttribute("groupByTypes", deviceService.groupByTypes());
        return "devices/allDevicesGroupByType";
    }

    @GetMapping("/groupByOwner")
    public String allDevicesGroupByOwner(Model model) {
        model.addAttribute("groupByOwners", deviceService.groupByOwner());
        return "devices/allDevicesGroupByOwner";
    }

    @GetMapping("/groupByAirfield")
    public String allDevicesGroupByAirfield(Model model) {
        model.addAttribute("groupByAirfields", deviceService.groupByAirfield());
        return "devices/allDevicesGroupByAirfield";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = typeService.findById(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceService.findAllByType(type));
        return "devices/devicesByType";
    }

    @GetMapping("owner/{id}")
    public String devicesByOwner(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        model.addAttribute("allDevices", deviceService.findAllByOwner(owner));
        return "devices/devicesByOwner";
    }

    @GetMapping("airfield/{id}")
    public String devicesByAirfield(@PathVariable("id") Long id, Model model) {
        Airfield airfield = airfieldService.findById(id);
        model.addAttribute("airfield", airfield);
        model.addAttribute("allDevices", deviceService.findAllByAirfield(airfield));
        return "devices/devicesByAirfield";
    }

    @GetMapping("group/{id}")
    public String devicesByGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        model.addAttribute("devicesByGroups", deviceService.findAllByGroup(group));
        return "devices/devicesByGroup";
    }

    @GetMapping("/groupByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groupByGroups", deviceService.groupByGroups());
        return "devices/allDevicesGroupByTypeOrderByGroup";
    }

    @GetMapping("/pivotTable")
    public String pivotTable(Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("superiors", ownerService.findAllSuperiors());
        model.addAttribute("deviceService", deviceService);
        return "devices/pivotTableDevicesByTypesAndOwners";
    }
}
