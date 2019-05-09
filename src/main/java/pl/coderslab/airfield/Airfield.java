package pl.coderslab.airfield;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.owner.Owner;

import javax.persistence.*;

@Entity
@Table(name = "airfields")
public class Airfield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String abbr;

    @ManyToOne
    private Owner owner;

    public Airfield() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
