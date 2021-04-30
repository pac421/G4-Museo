package bean;

import java.util.ArrayList;
import java.util.Date;

public class Lend extends Work {
    private Date start, end;
    private String lender;

    public Lend() {
    }

    /*
     * Constructor for creating a Lend that does not exists yet in DB
     */
    public Lend(String label, String description, String period, double height, double width, double depth, double weight, Collection collection, Category category, ArrayList<Artist> madeBy, Date start, Date end, String lender) {
        super(label, description, period, height, width, depth, weight, collection, category, madeBy);
        this.start = start;
        this.end = end;
        this.lender = lender;
    }

    /*
     * Constructor for LendDAO
     */
    public Lend(String id, String label, String description, String period, double height, double width, double depth, double weight, Date deletedAt, User deletedBy, Collection collection, Category category, ArrayList<Artist> madeBy, ArrayList<Picture> pictures, Date start, Date end, String lender) {
        super(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category, madeBy, pictures);
        this.start = start;
        this.end = end;
        this.lender = lender;
    }


    @Override
    public String toString() {
        return "Lend n°" + id + " from " + lender;
    }
}
