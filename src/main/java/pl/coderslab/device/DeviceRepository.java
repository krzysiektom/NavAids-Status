package pl.coderslab.device;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.owner.Owner;
import pl.coderslab.type.Type;

import java.util.List;

interface DeviceRepository extends JpaRepository<Device, Long> {
    Long countByType(Type type);

    Long countByTypeAndReadyFalse(Type type);

    Long countByTypeAndReadyTrue(Type type);

    List<Device> findAllByType(Type type);

    Long countByOwnerAndType(Owner owner, Type type);

    Long countByOwnerAndTypeAndReadyTrue(Owner owner, Type type);

    List<Device> findAllByOwner(Owner owner);

    List<Device> findAllByAirfield(Airfield airfield);
}
