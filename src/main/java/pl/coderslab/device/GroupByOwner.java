package pl.coderslab.device;

import pl.coderslab.owner.Owner;

import java.util.List;

public class GroupByOwner {
    private Owner owner;
    private List<Device> devices;

    public GroupByOwner(Owner owner, List<Device> devices) {
        this.owner = owner;
        this.devices = devices;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
