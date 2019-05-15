package pl.coderslab.device;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DeviceDTO extends CrudRepository<Device, Long> {

    @Query("SELECT new pl.coderslab.device.DevicesCountByType(d.type, " +
            "count(d), " +
            "sum (CASE d.ready WHEN true THEN 1 WHEN false then 0 ELSE null END), " +
            "sum (CASE d.ready WHEN true THEN 0 WHEN false then 1 ELSE null END))" +
            "FROM Device d group by d.type)")
    List<DevicesCountByType> countByTypes();

    @Query("SELECT NEW pl.coderslab.device.DevicesCountByAirfieldAndGroup(d.airfield," +
            "t.group," +
            "COUNT (d)," +
            "sum (CASE d.ready WHEN true THEN 1 WHEN false then 0 ELSE null END), " +
            "sum (CASE d.ready WHEN true THEN 0 WHEN false then 1 ELSE null END))" +
            "FROM Device d,Type t where d.type=t GROUP BY d.airfield, t.group)")
    List<DevicesCountByAirfieldAndGroup> countByAirfieldAndGroup();


}
