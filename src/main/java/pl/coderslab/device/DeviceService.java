package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.group.Group;
import pl.coderslab.owner.Owner;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeService typeService;

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
        List<Type> types = typeService.findAllByGroup(group);
        return types.stream()
                .map(type -> deviceRepository.countByOwnerAndType(owner, type))
                .reduce(0L, Long::sum);
    }

    public Long countByOwnerAndGroupAndReadyTrue(Owner owner, Group group) {
        List<Type> types = typeService.findAllByGroup(group);
        return types.stream()
                .map(type -> deviceRepository.countByOwnerAndTypeAndReadyTrue(owner, type))
                .reduce(0L, Long::sum);
    }

    public List<GroupByType> groupByTypes() {
        List<Type> types = typeService.findAll();
        return types.stream()
                .map(type -> new GroupByType(type.getName(), countByType(type), countByTypeAndReadyTrue(type), countByTypeAndReadyFalse(type), type.getId()))
                .collect(Collectors.toList());
    }

}
