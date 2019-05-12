package pl.coderslab.device;

import pl.coderslab.type.Type;

public class GroupTest {
    private Type type;
    private Long Ready;

    public GroupTest() {
    }

    public GroupTest(Type type, Long ready) {
        this.type = type;
        Ready = ready;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getReady() {
        return Ready;
    }

    public void setReady(Long ready) {
        Ready = ready;
    }
}
