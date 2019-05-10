package pl.coderslab.fix;

import pl.coderslab.failure.Failure;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fixes")
public class Fix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Failure failure;
    private String done;
    private String usedMaterials;
    private LocalDateTime created;

    public Fix() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getUsedMaterials() {
        return usedMaterials;
    }

    public void setUsedMaterials(String usedMaterials) {
        this.usedMaterials = usedMaterials;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
