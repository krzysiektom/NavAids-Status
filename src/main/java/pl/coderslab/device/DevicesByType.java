package pl.coderslab.device;

import pl.coderslab.type.Type;

import java.util.List;

public class DevicesByType {
private Type type;
private List<Device>devices;

    public DevicesByType(Type type, List<Device> devices) {
        this.type = type;
        this.devices = devices;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
