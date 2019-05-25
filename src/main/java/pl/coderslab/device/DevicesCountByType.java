package pl.coderslab.device;

import pl.coderslab.type.Type;

public class DevicesCountByType {
    private Type type;
    private Long count;
    private Long ready;
    private Long underService;

    public DevicesCountByType() {
    }

    public DevicesCountByType(Type type, Long count, Long ready, Long underService) {
        this.type = type;
        this.count = count;
        this.ready = ready;
        this.underService = underService;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "DevicesCountByType{" +
                "type=" + type +
                ", count=" + count +
                ", ready=" + ready +
                ", underService=" + underService +
                '}';
    }
}
