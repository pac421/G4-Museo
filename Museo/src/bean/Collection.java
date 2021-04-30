package bean;

import java.util.UUID;

public class Collection {

    private String id,label,period;

    public Collection(){
    }

    /*
     * Constructor for creating a Collection that does not exists yet in DB
     */
    public Collection(String label, String period){
        this.id = UUID.randomUUID().toString();
        this.label = label;
        this.period= period;
    }

    /*
     * Constructor for CollectionDAO
     */
    public Collection(String id, String label, String period) {
        this.id = id;
        this.label = label;
        this.period = period;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + period ;
    }
}
