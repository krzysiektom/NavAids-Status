package pl.coderslab.device;

import pl.coderslab.group.Group;

import java.util.List;

public class DevicesCountByTypeOrderByGroup {
    private Group group;
    private List<DevicesCountByType> devicesCountByTypes;

    public DevicesCountByTypeOrderByGroup(Group group, List<DevicesCountByType> devicesCountByTypes) {
        this.group = group;
        this.devicesCountByTypes = devicesCountByTypes;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<DevicesCountByType> getDevicesCountByTypes() {
        return devicesCountByTypes;
    }

    public void setDevicesCountByTypes(List<DevicesCountByType> devicesCountByTypes) {
        this.devicesCountByTypes = devicesCountByTypes;
    }
}
