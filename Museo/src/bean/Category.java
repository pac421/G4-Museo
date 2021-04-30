package bean;
import java.util.UUID;

public class Category {
    private String id, label;

    public Category() {
    }

    public Category(String label) {
        this.id = UUID.randomUUID().toString();
        this.label = label;
    }

    public Category(String id, String label) {
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
        return id + " - " + label ;
    }
}


