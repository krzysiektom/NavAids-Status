package pl.coderslab.device;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final TypeRepository typeRepository;
    private final OwnerRepository ownerRepository;
    private final AirfieldRepository airfieldRepository;
    private final GroupRepository groupRepository;
    private final DeviceRepository deviceRepository;

    public DeviceController(DeviceService deviceService, TypeRepository typeRepository, OwnerRepository ownerRepository, AirfieldRepository airfieldRepository, GroupRepository groupRepository, DeviceRepository deviceRepository) {
        this.deviceService = deviceService;
        this.typeRepository = typeRepository;
        this.ownerRepository = ownerRepository;
        this.airfieldRepository = airfieldRepository;
        this.groupRepository = groupRepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/")
    public String allDevices(Model model) {
        model.addAttribute("allDevices", deviceRepository.findAll());
        return "devices/allDevices";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("airfields", airfieldRepository.findAll());
        return "devices/deviceForm";
    }

    @PostMapping("/add")
    public String addDevice(@ModelAttribute("device") @Valid Device device, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("owners", ownerRepository.findAll());
            model.addAttribute("types", typeRepository.findAll());
            model.addAttribute("airfields", airfieldRepository.findAll());
            return "devices/deviceForm";
        }
        device.setReady(true);
        deviceRepository.save(device);
        return "redirect:/devices/" + device.getId();
    }

    @GetMapping("/{id}")
    public String deviceDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("device", deviceRepository.findOne(id));
        return "devices/devicePage";
    }

    @GetMapping("type/{id}")
    public String devicesByType(@PathVariable("id") Long id, Model model) {
        Type type = typeRepository.findOne(id);
        model.addAttribute("type", type);
        model.addAttribute("allDevices", deviceRepository.findAllByType(type));
        return "devices/devicesByType";
    }

    @GetMapping("owner/{id}")
    public String devicesByOwner(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerRepository.findOne(id);
        model.addAttribute("owner", owner);
        model.addAttribute("allDevices", deviceRepository.findAllByOwner(owner));
        return "devices/devicesByOwner";
    }

    @GetMapping("superior/{id}")
    public String devicesBySuperior(@PathVariable("id") Long id, Model model) {
        Owner superior = ownerRepository.findOne(id);
        model.addAttribute("superior", superior);
        model.addAttribute("groupByOwners", deviceService.findAllBySuperiorGroupByOwner(superior));
        return "devices/devicesBySuperior";
    }

    @GetMapping("airfield/{id}")
    public String devicesByAirfield(@PathVariable("id") Long id, Model model) {
        Airfield airfield = airfieldRepository.findOne(id);
        model.addAttribute("airfield", airfield);
        model.addAttribute("allDevices", deviceRepository.findAllByAirfield(airfield));
        return "devices/devicesByAirfield";
    }

    @GetMapping("group/{id}")
    public String devicesByGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupRepository.findOne(id);
        model.addAttribute("group", group);
        model.addAttribute("devicesByGroups", deviceService.findAllByGroup(group));
        return "devices/devicesByGroup";
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

    @GetMapping("/countByTypes")
    public String allDevicesGroupByType(Model model) {
        model.addAttribute("devicesCountByTypes", deviceRepository.countByTypes());
        return "devices/allDevicesCountByType";
    }

    @GetMapping("/countByTypes/{id}")
    public String allDevicesCountByTypeGroupByGroup(@PathVariable Long id, Model model) {
        Group group = groupRepository.findOne(id);
        List<DevicesCountByType> devicesCountByTypes = deviceRepository.countByTypes(group);
        if (devicesCountByTypes.size() == 1) {
            return "redirect:/devices/group/" + id;
        }
        model.addAttribute("group", group);
        model.addAttribute("devicesCountByTypes", devicesCountByTypes);
        model.addAttribute("sum", deviceService.countByGroup(group));
        return "devices/allDevicesCountByTypeGroupByGroup";
    }

    @GetMapping("/countByTypeOrderByGroup")
    public String allDevicesGroupByTypeOrderByGroup(Model model) {
        model.addAttribute("groupByGroups", deviceService.groupByGroups());
        return "devices/allDevicesCountByTypeOrderByGroup";
    }

    @GetMapping("/pivotTable")
    public String pivotTable(Model model) {
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("pivotTable", deviceService.getPivotTableByAirfieldAndGroup());
        model.addAttribute("sums", deviceService.countByGroup());
        return "devices/pivotTableDevicesByTypesAndOwners";
    }
}
