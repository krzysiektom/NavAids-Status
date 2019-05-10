package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private AirfieldRepository airfieldRepository;

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

    public Long countByType(Type type) {
        return deviceRepository.countByType(type);
    }

    public Long countByTypeAndReadyTrue(Type type) {
        return deviceRepository.countByTypeAndReadyTrue(type);
    }

    public Long countByTypeAndReadyFalse(Type type) {
        return deviceRepository.countByTypeAndReadyFalse(type);
    }

    public Long countByOwnerAndGroup(Owner owner, Group group) {
        List<Type> types = typeRepository.findAllByGroup(group);
        return types.stream()
                .map(type -> deviceRepository.countByOwnerAndType(owner, type))
                .reduce(0L, Long::sum);
    }

    public Long countByOwnerAndGroupAndReadyTrue(Owner owner, Group group) {
        List<Type> types = typeRepository.findAllByGroup(group);
        return types.stream()
                .map(type -> deviceRepository.countByOwnerAndTypeAndReadyTrue(owner, type))
                .reduce(0L, Long::sum);
    }

    public Airfield findAirfieldByOwner(Owner owner) {
        return airfieldRepository.findByOwner(owner);
    }

    public List<Airfield> findAllAirfields() {
        return airfieldRepository.findAll();
    }

    public Type findTypeById(Long id) {
        return typeRepository.findOne(id);
    }

    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public List<Type> findAllTypesByGroup(Group group) {
        return typeRepository.findAllByGroup(group);
    }

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public List<Owner> findAllSuperiors() {
        return ownerRepository.findAllBySuperior(ownerRepository.findOne(1L));
    }

    public List<Owner> findAllOwnersBySuperior(Owner superior) {
        return ownerRepository.findAllBySuperior(superior);
    }

    public List<Owner> findAllOwners() {
        return findAllSuperiors().stream()
                .map(owner -> ownerRepository.findAllBySuperior(owner))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
