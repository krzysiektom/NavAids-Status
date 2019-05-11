package pl.coderslab.device;

import pl.coderslab.group.Group;

import java.util.List;

public class GroupByGroup {
    private Group group;
    private List<GroupByType> groupByTypes;

    public GroupByGroup(Group group, List<GroupByType> groupByTypes) {
        this.group = group;
        this.groupByTypes = groupByTypes;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<GroupByType> getGroupByTypes() {
        return groupByTypes;
    }

    public void setGroupByTypes(List<GroupByType> groupByTypes) {
        this.groupByTypes = groupByTypes;
    }
}
