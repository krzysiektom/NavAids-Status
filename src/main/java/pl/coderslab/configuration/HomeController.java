package pl.coderslab.configuration;

import antlr.ASTNULLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.device.DeviceService;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.OwnerService;

@Controller
@SessionAttributes({"AllAirfields", "AllOwners"})
public class HomeController {
    private final AirfieldRepository airfieldRepository;
    private final OwnerService ownerService;
    private final GroupRepository groupRepository;
    private final DeviceService deviceService;

    public HomeController(AirfieldRepository airfieldRepository, OwnerService ownerService, GroupRepository groupRepository, DeviceService deviceService) {
        this.airfieldRepository = airfieldRepository;
        this.ownerService = ownerService;
        this.groupRepository = groupRepository;
        this.deviceService = deviceService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("AllAirfields", airfieldRepository.findAll());
        model.addAttribute("AllOwners", ownerService.findAllBySuperior());
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("pivotTable", deviceService.getPivotTableByAirfieldAndGroup());
        model.addAttribute("sums", deviceService.countByGroup());
        return "index";
    }
}
