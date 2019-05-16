package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldService;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupService;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AirfieldService airfieldService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private GroupService groupService;
    @Autowired
    DeviceDTO deviceDTO;

    public Device findById(Long id) {
        return deviceRepository.findOne(id);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public List<Device> findAllByType(Type type) {
        return deviceRepository.findAllByType(type);
    }

    public List<Device> findAllByOwner(Owner owner) {
        return deviceRepository.findAllByOwner(owner);
    }

    public List<Device> findAllByAirfield(Airfield airfield) {
        return deviceRepository.findAllByAirfield(airfield);
    }

    public List<DevicesByOwner> groupByOwner() {
        List<Owner> owners = ownerService.findAllOwners();
        List<Device> devices = deviceRepository.findAll();

        return owners.stream()
                .map(owner -> new DevicesByOwner(
                                owner,
                                devices.stream().filter(device -> device.getOwner().equals(owner)).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    public List<DevicesByOwner> findAllBySuperiorGroupByOwner(Owner superior) {
        List<Owner> owners = ownerService.findAllBySuperior(superior);
        List<Device> devices = deviceRepository.findAllByOwnerIn(owners);

        return owners.stream()
                .map(owner -> new DevicesByOwner(
                                owner,
                                devices.stream().filter(device -> device.getOwner().equals(owner)).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    public List<DevicesByAirfield> groupByAirfield() {
        List<Airfield> airfields = airfieldService.findAll();
        List<Device> devices = deviceRepository.findAll();

        return airfields.stream()
                .map(airfield -> new DevicesByAirfield(
                                airfield,
                                devices.stream().filter(device -> device.getAirfield().equals(airfield)).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    public List<DevicesCountByTypeOrderByGroup> groupByGroups() {
        List<Group> groups = groupService.findAll();
        List<Type> types = typeService.findAll();
        List<DevicesCountByType> devicesCountByTypes = deviceDTO.countByTypes();

        return groups.stream()
                .map(group ->
                        new DevicesCountByTypeOrderByGroup(
                                group,
                                types.stream()
                                        .filter(type -> type.getGroup().equals(group))
                                        .map(type -> devicesCountByTypes.stream()
                                                .filter(devicesCountByType -> devicesCountByType.getType().equals(type))
                                                .collect(Collectors.toList()))
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    public List<DevicesByType> findAllByGroup(Group group) {
        List<Type> types = typeService.findAllByGroup(group);
        List<Device> devices = deviceRepository.findAllByTypeIn(types);

        return types.stream()
                .map(type -> new DevicesByType(
                                type,
                                devices.stream()
                                        .filter(device -> device.getType().equals(type))
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    public List<DevicesPivotTableByAirfieldAndGroup> getPivotTableByAirfieldAndGroup() {
        List<Airfield> airfields = airfieldService.findAll();
        List<DevicesCountByAirfieldAndGroup> devicesCountByAirfieldAndGroups = deviceDTO.countByAirfieldAndGroup();

        return airfields.stream()
                .map(airfield -> new DevicesPivotTableByAirfieldAndGroup(
                        airfield,
                        devicesCountByAirfieldAndGroups.stream()
                                .filter(devicesCountByAirfieldAndGroup -> devicesCountByAirfieldAndGroup.getAirfield().equals(airfield))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesCountByGroup> countByGroup() {
        List<Group> groups = groupService.findAll();
        List<DevicesCountByAirfieldAndGroup> dCs = deviceDTO.countByAirfieldAndGroup();

        return groups.stream()
                .map(group -> new DevicesCountByGroup(group,
                                dCs.stream()
                                        .filter(dC -> dC.getGroup().equals(group))
                                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getCount(), Long::sum),
                                dCs.stream()
                                        .filter(dC -> dC.getGroup().equals(group))
                                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getReady(), Long::sum),
                                dCs.stream()
                                        .filter(dC -> dC.getGroup().equals(group))
                                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getUnderService(), Long::sum)
                        )
                )
                .collect(Collectors.toList());
    }

    public DevicesCountByGroup countByGroup(Group group) {
        List<DevicesCountByType> dCs = deviceDTO.countByTypes(group);

        return new DevicesCountByGroup(group,
                dCs.stream()
                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getCount(), Long::sum),
                dCs.stream()
                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getReady(), Long::sum),
                dCs.stream()
                        .reduce(0L, (partialCountResult, dc) -> partialCountResult + dc.getUnderService(), Long::sum)
        );
    }

}
