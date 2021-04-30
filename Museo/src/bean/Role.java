package bean;

import java.util.UUID;

public class Role {
    private String id, label;

    public Role() {
    }

    /*
     * Constructor for creating a Role that does not exists yet in DB
     */
    public Role(String label) {
        this.id = UUID.randomUUID().toString();
        this.label = label;
    }

    /*
     * Constructor for RoleDAO
     */
    public Role(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    @Override
    public String toString() {
        return "Role : " + label;
    }
}
