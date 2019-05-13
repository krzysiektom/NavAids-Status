package pl.coderslab.device;

import pl.coderslab.type.Type;

import javax.persistence.*;

/*
@SqlResultSetMapping(
        name = "GroupTestResult",
        entities = {
                @EntityResult(
                        entityClass = pl.coderslab.device.GroupTest.class,
                        fields = {@FieldResult(name = "id", column = "type_d"),
                                @FieldResult(name = "ready", column = "ready")
                        }
                )
        }
)

@NamedNativeQuery(
        name="GroupTests",
        query="select type_id, count(ready) as ready from devices group by type_id",
        resultSetMapping = "GroupTestResult"
)*/
public class GroupTest {

    private Type type;
    private Long ready;

    public GroupTest() {
    }

    public GroupTest(Type type, Long ready) {
        this.type = type;
        this.ready = ready;
    }

    public Type getTypeId() {
        return type;
    }

    public void setTypeId(Type type) {
        this.type = type;
    }

    public Long getReady() {
        return ready;
    }

    public void setReady(Long ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "GroupTest{" +
                "type=" + type +
                ", ready=" + ready +
                '}';
    }
}
