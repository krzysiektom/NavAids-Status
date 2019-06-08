package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AirfieldRepository airfieldRepository;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private GroupRepository groupRepository;

    public List<Device> devicesByGroup(Long idGroup){
        return deviceRepository.findAllByTypeIn(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)));
    }

    public List<Device> devicesByGroupAndReady(Long idGroup){
        return deviceRepository.findAllByTypeInAndReady(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)),true);
    }

    public List<Device> devicesByGroupAndUnderService(Long idGroup){
        return deviceRepository.findAllByTypeInAndReady(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)),false);
    }

    public List<Device> devicesByGroupAndAirfield(Long idGroup, Long idAirfield){
        return deviceRepository.findAllByTypeInAndAirfield(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)),airfieldRepository.findOne(idAirfield));
    }

    public List<Device> devicesByGroupAndAirfieldAndReady(Long idGroup, Long idAirfield){
        return deviceRepository.findAllByTypeInAndAirfieldAndReady(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)),airfieldRepository.findOne(idAirfield),true);
    }

    public List<Device> devicesByGroupAndAirfieldAndUnderService(Long idGroup, Long idAirfield){
        return deviceRepository.findAllByTypeInAndAirfieldAndReady(typeRepository.findAllByGroup(groupRepository.findOne(idGroup)),airfieldRepository.findOne(idAirfield),false);
    }




    public List<DevicesByOwner> groupByOwner() {
        List<Owner> owners = ownerService.findAllOwners();
        List<Device> devices = deviceRepository.findAll();

        return owners.stream()
                .map(owner -> new DevicesByOwner(
                        owner,
                        devices.stream().filter(device -> device.getOwner().equals(owner)).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesByOwner> findAllBySuperiorGroupByOwner(Owner superior) {
        List<Owner> owners = ownerRepository.findAllBySuperior(superior);
        List<Device> devices = deviceRepository.findAllByOwnerIn(owners);

        return owners.stream()
                .map(owner -> new DevicesByOwner(
                        owner,
                        devices.stream()
                                .filter(device -> device.getOwner().equals(owner))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesByAirfield> groupByAirfield() {
        List<Airfield> airfields = airfieldRepository.findAll();
        List<Device> devices = deviceRepository.findAll();

        return airfields.stream()
                .map(airfield -> new DevicesByAirfield(
                        airfield,
                        devices.stream()
                                .filter(device -> device.getAirfield().equals(airfield))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesCountByTypeOrderByGroup> groupByGroups() {
        List<Group> groups = groupRepository.findAll();
        List<Type> types = typeRepository.findAll();
        List<DevicesCountByType> devicesCountByTypes = deviceRepository.countByTypes();

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
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesByType> findAllByGroup(Group group) {
        List<Type> types = typeRepository.findAllByGroup(group);
        List<Device> devices = deviceRepository.findAllByTypeIn(types);

        return types.stream()
                .map(type -> new DevicesByType(
                        type,
                        devices.stream()
                                .filter(device -> device.getType().equals(type))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesPivotTableByAirfieldAndGroup> getPivotTableByAirfieldAndGroup() {
        List<Airfield> airfields = airfieldRepository.findAll();
        List<DevicesCountByAirfieldAndGroup> devicesCountByAirfieldAndGroups = deviceRepository.countByAirfieldAndGroup();

        return airfields.stream()
                .map(airfield -> new DevicesPivotTableByAirfieldAndGroup(
                        airfield,
                        devicesCountByAirfieldAndGroups.stream()
                                .filter(devicesCountByAirfieldAndGroup -> devicesCountByAirfieldAndGroup.getAirfield().equals(airfield))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<DevicesCountByGroup> countByGroup() {
        List<Group> groups = groupRepository.findAll();
        List<DevicesCountByAirfieldAndGroup> dCs = deviceRepository.countByAirfieldAndGroup();

        return groups.stream()
                .map(group -> {
                    DevicesCountByGroup devicesCountByGroup = new DevicesCountByGroup(group);
                    dCs.stream()
                            .filter(dC -> dC.getGroup().equals(group))
                            .forEach(dc -> {
                                devicesCountByGroup.addCount(dc.getCount());
                                devicesCountByGroup.addReady(dc.getReady());
                                devicesCountByGroup.addUnderService(dc.getUnderService());
                            });
                    return devicesCountByGroup;
                })
                .collect(Collectors.toList());
    }

    public DevicesCountByGroup countByGroup(Group group) {
        List<DevicesCountByType> dCs = deviceRepository.countByTypes(group);

        DevicesCountByGroup devicesCountByGroup = new DevicesCountByGroup(group);
        dCs.forEach(dc -> {
            devicesCountByGroup.addCount(dc.getCount());
            devicesCountByGroup.addReady(dc.getReady());
            devicesCountByGroup.addUnderService(dc.getUnderService());
        });
        return devicesCountByGroup;
    }

}
