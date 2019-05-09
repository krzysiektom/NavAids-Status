package pl.coderslab.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.Group;
import pl.coderslab.owner.Owner;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private AirfieldRepository airfieldRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device findById(Long id) {
        return deviceRepository.findOne(id);
    }

    public List<Device> findAllByType(Type type) {
        return deviceRepository.findAllByType(type);
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
        //return types.stream().map(type -> deviceRepository.countByOwnerAndType(owner, type)).count(); //żle liczy
        if (null == types) {
            return 0L;
        } else {
            Long result = 0L;
            for (Type type : types) {
                result += deviceRepository.countByOwnerAndType(owner, type);
            }
            return result;
        }
    }

    public Long countByOwnerAndGroupAndReadyTrue(Owner owner, Group group) {
        List<Type> types = typeRepository.findAllByGroup(group);
        if (null == types) {
            return 0L;
        } else {
            Long result = 0L;
            for (Type type : types) {
                result += deviceRepository.countByOwnerAndTypeAndReadyTrue(owner, type);
            }
            return result;
        }
    }

    public List<Owner> findAllOwnersBySuperior(Owner superior) {
        return ownerRepository.findAllBySuperior(superior);
    }

    public Airfield findAirfieldByOwner(Owner owner) {
        return airfieldRepository.findByOwner(owner);
    }

    public List<Type> findAllTypesByGroup(Group group) {
        return typeRepository.findAllByGroup(group);
    }
}
