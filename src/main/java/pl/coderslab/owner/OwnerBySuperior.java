package pl.coderslab.owner;

import java.util.List;

public class OwnerBySuperior {
    private Owner superior;
    private List<Owner> owners;

    public OwnerBySuperior(Owner superior, List<Owner> owners) {
        this.superior = superior;
        this.owners = owners;
    }

    public Owner getSuperior() {
        return superior;
    }

    public void setSuperior(Owner superior) {
        this.superior = superior;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
}
