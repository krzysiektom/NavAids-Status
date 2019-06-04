package pl.coderslab.device;

import pl.coderslab.group.Group;

public class DevicesCountByGroup {
    private Group group;
    private Long count = 0L;
    private Long ready= 0L;
    private Long underService= 0L;

    public DevicesCountByGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getCount() {
        return count;
    }

    public void addCount(Long count) {
        this.count += count;
    }

    public Long getReady() {
        return ready;
    }

    public void setReady(Long ready) {
        this.ready = ready;
    }

    public void addReady(Long ready) {
        this.ready += ready;
    }

    public Long getUnderService() {
        return underService;
    }

    public void addUnderService(Long underService) {
        this.underService += underService;
    }
}
