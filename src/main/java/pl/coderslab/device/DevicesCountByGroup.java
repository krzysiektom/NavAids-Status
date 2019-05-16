package pl.coderslab.device;

import pl.coderslab.group.Group;

public class DevicesCountByGroup {
    private Group group;
    private Long count;
    private Long ready;
    private Long underService;

    public DevicesCountByGroup(Group group, Long count, Long ready, Long underService) {
        this.group = group;
        this.count = count;
        this.ready = ready;
        this.underService = underService;
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

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getReady() {
        return ready;
    }

    public void setReady(Long ready) {
        this.ready = ready;
    }

    public Long getUnderService() {
        return underService;
    }

    public void setUnderService(Long underService) {
        this.underService = underService;
    }
}
