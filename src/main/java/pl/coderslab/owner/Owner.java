package pl.coderslab.owner;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String abbr;
    @ManyToOne
    private Owner superior;

    public Owner() {
    }

    public Owner(Long id, Owner superior) {
        this.id = id;
        this.superior = superior;
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

    public Owner getSuperior() {
        return superior;
    }

    public void setSuperior(Owner superior) {
        this.superior = superior;
    }
}

