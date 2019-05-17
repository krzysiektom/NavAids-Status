package pl.coderslab.fix;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.failure.Failure;
import pl.coderslab.user.User;

import javax.jws.soap.SOAPBinding;
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

    @ManyToOne
    private User user;

    @NotBlank
    private String done;

    @NotBlank
    private String usedMaterials;

    private LocalDateTime created;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Fix{" +
                "id=" + id +
                ", failure=" + failure +
                ", user=" + user +
                ", done='" + done + '\'' +
                ", usedMaterials='" + usedMaterials + '\'' +
                ", created=" + created +
                '}';
    }
}
