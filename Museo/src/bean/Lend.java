package bean;

import java.util.ArrayList;
import java.sql.Date;

public class Lend extends Work {
    private Date start, end;
    private String lender;

    public Lend() {
    }

    /*
     * Constructor for creating a Lend that does not exists yet in DB
     */
    public Lend(String label, String description, String period, double height, double width, double depth, double weight, Collection collection, Category category, Date start, Date end, String lender) {
        super(label, description, period, height, width, depth, weight, collection, category);
        this.start = start;
        this.end = end;
        this.lender = lender;
    }

    /*
     * Constructor for LendDAO
     */
    public Lend(String id, String label, String description, String period, double height, double width, double depth, double weight, Date deletedAt, User deletedBy, Collection collection, Category category, Date start, Date end, String lender) {
        super(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category);
        this.start = start;
        this.end = end;
        this.lender = lender;
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

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    @Override
    public String toString() {
        return "Lend n°" + id + " from " + lender;
    }
}
