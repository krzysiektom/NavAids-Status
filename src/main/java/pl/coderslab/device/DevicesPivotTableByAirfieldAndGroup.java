package pl.coderslab.device;

import pl.coderslab.airfield.Airfield;

import java.util.List;

public class DevicesPivotTableByAirfieldAndGroup {
    private Airfield airfield;
    private List<DevicesCountByAirfieldAndGroup> devicesCountByAirfieldAndGroups;

    public DevicesPivotTableByAirfieldAndGroup(Airfield airfield, List<DevicesCountByAirfieldAndGroup> devicesCountByAirfieldAndGroups) {
        this.airfield = airfield;
        this.devicesCountByAirfieldAndGroups = devicesCountByAirfieldAndGroups;
    }

    public Airfield getAirfield() {
        return airfield;
    }

    public void setAirfield(Airfield airfield) {
        this.airfield = airfield;
    }

    public List<DevicesCountByAirfieldAndGroup> getDevicesCountByAirfieldAndGroups() {
        return devicesCountByAirfieldAndGroups;
    }

    public void setDevicesCountByAirfieldAndGroups(List<DevicesCountByAirfieldAndGroup> devicesCountByAirfieldAndGroups) {
        this.devicesCountByAirfieldAndGroups = devicesCountByAirfieldAndGroups;
    }
}
