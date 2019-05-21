package pl.coderslab.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.group.Group;
import pl.coderslab.owner.Owner;
import pl.coderslab.type.Type;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT new pl.coderslab.device.DevicesCountByType(d.type, " +
            "count(d), " +
            "sum (CASE d.ready WHEN true THEN 1 WHEN false then 0 ELSE null END), " +
            "sum (CASE d.ready WHEN true THEN 0 WHEN false then 1 ELSE null END))" +
            "FROM Device d group by d.type)")
    List<DevicesCountByType> countByTypes();

    @Query("SELECT new pl.coderslab.device.DevicesCountByType(d.type, " +
            "count(d), " +
            "sum (CASE d.ready WHEN true THEN 1 WHEN false then 0 ELSE null END), " +
            "sum (CASE d.ready WHEN true THEN 0 WHEN false then 1 ELSE null END))" +
            "FROM Device d WHERE d.type.group = ?1 group by d.type)")
    List<DevicesCountByType> countByTypes(Group group);

    @Query("SELECT NEW pl.coderslab.device.DevicesCountByAirfieldAndGroup(d.airfield," +
            "t.group," +
            "COUNT (d)," +
            "sum (CASE d.ready WHEN true THEN 1 WHEN false then 0 ELSE null END), " +
            "sum (CASE d.ready WHEN true THEN 0 WHEN false then 1 ELSE null END))" +
            "FROM Device d,Type t where d.type=t GROUP BY d.airfield, t.group)")
    List<DevicesCountByAirfieldAndGroup> countByAirfieldAndGroup();

    List<Device> findAllByType(Type type);

    List<Device> findAllByTypeIn(List<Type> types);

    List<Device> findAllByOwner(Owner owner);

    List<Device> findAllByOwnerIn(List<Owner> owners);

    List<Device> findAllByAirfield(Airfield airfield);

    List<Device> findAllByAirfieldIn(List<Airfield> airfields);
}
