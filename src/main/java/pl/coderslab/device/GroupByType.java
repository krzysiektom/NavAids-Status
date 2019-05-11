package pl.coderslab.device;

public class GroupByType {
    private String typeName;
    private Long count;
    private Long ready;
    private Long underService;
    private Long typeId;

    public GroupByType(String typeName, Long count, Long ready, Long underService, Long typeId) {
        this.typeName = typeName;
        this.count = count;
        this.ready = ready;
        this.underService = underService;
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
