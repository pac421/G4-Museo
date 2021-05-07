package bean;

import java.sql.Date;
import java.util.UUID;

public class PropertyState {
    private Property property;
    private State state;
    private Date start, end;
    private String id, comment;

    public PropertyState() {
    }

    /*
     * Constructor for creating an ArtistWork that does not exists yet in DB
     */
    public PropertyState(Property property, State state, Date start, Date end, String comment) {
        this.id = UUID.randomUUID().toString();
        this.property = property;
        this.state = state;
        this.start = start;
        this.end = end;
        this.comment = comment;
    }

    /*
     * Constructor for ArtistWorkDAO
     */
    public PropertyState(String id, Property property, State state, Date start, Date end, String comment) {
        this.id = id;
        this.property = property;
        this.state = state;
        this.start = start;
        this.end = end;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Property : " + property.getLabel() + " - State : " + state.getLabel();
    }
}
