package pl.coderslab.device;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.owner.Owner;
import pl.coderslab.type.Type;

import java.util.List;

interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByType(Type type);

    List<Device> findAllByTypeIn(List<Type> types);

    List<Device> findAllByOwner(Owner owner);

    List<Device> findAllByOwnerIn(List<Owner> owners);

    List<Device> findAllByAirfield(Airfield airfield);

    List<Device> findAllByAirfieldIn(List<Airfield> airfields);
}
