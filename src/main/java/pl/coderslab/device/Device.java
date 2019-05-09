package pl.coderslab.device;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.type.Type;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Type type;

    @NotBlank
    private String factoryNumber;

    private boolean status;

    public Device() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
