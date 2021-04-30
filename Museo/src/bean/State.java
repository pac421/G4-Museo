package bean;

import java.util.UUID;

public class State {
    private String id, label;

    public State() {
    }

    /*
     * Constructor for creating a State that does not exists yet in DB
     */
    public State(String label) {
        this.id = UUID.randomUUID().toString();
        this.label = label;
    }

    /*
     * Constructor for StateDAO
     */
    public State(String id, String label) {
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
        return "State : " + label;
    }
}
