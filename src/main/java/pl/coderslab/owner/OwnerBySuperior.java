package pl.coderslab.owner;

import java.util.List;
import java.util.Objects;

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

    public List<Owner> getOwners() {
        return owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerBySuperior)) return false;
        OwnerBySuperior that = (OwnerBySuperior) o;
        return Objects.equals(getSuperior(), that.getSuperior()) &&
                Objects.equals(getOwners(), that.getOwners());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuperior(), getOwners());
    }
}
