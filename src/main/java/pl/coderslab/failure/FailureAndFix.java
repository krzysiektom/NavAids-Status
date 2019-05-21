package pl.coderslab.failure;

import org.hibernate.validator.constraints.NotBlank;

public class FailureAndFix {
    @NotBlank
    private String description;

    @NotBlank
    private String done;

    @NotBlank
    private String usedMaterials;

    public FailureAndFix() {
    }

    public FailureAndFix(String description, String done, String usedMaterials) {
        this.description = description;
        this.done = done;
        this.usedMaterials = usedMaterials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "FailureAndFix{" +
                "description='" + description + '\'' +
                ", done='" + done + '\'' +
                ", usedMaterials='" + usedMaterials + '\'' +
                '}';
    }
}
