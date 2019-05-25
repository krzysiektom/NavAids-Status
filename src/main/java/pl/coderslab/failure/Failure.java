package pl.coderslab.failure;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.device.Device;
import pl.coderslab.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "failures")
public class Failure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Device device;

    @ManyToOne
    private User user;

    @NotBlank
    private String description;
    private boolean isFixed;
    private LocalDateTime created;
    private LocalDateTime finished;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }


    public Failure() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }

}
