package bean;

import java.util.Date;

public class PropertyState {
    private Property property;
    private State state;
    private Date start, end;
    private String comment;

    public PropertyState() {
    }

    public PropertyState(Property property, State state, Date start, Date end, String comment) {
        this.property = property;
        this.state = state;
        this.start = start;
        this.end = end;
        this.comment = comment;
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
