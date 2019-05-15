package pl.coderslab.device;

import pl.coderslab.airfield.Airfield;

import java.util.List;

public class DevicesByAirfield {
    private Airfield airfield;
    private List<Device> devices;

    public DevicesByAirfield(Airfield airfield, List<Device> devices) {
        this.airfield = airfield;
        this.devices = devices;
    }

    public Airfield getAirfield() {
        return airfield;
    }

    public void setAirfield(Airfield airfield) {
        this.airfield = airfield;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
