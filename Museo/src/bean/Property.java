package bean;

import java.util.ArrayList;
import java.util.Date;

public class Property extends Work {
    private Date ownedAt;
    private String ownedFrom;
    private double price;
    private PropertyState propertyState;

    public Property() {
    }

    /*
     * Constructor for creating a Property that does not exists yet in DB
     */
    public Property(String label, String description, String period, double height, double width, double depth, double weight, Collection collection, Category category, ArrayList<Artist> madeBy, Date ownedAt, String ownedFrom, double price) {
        super(label, description, period, height, width, depth, weight, collection, category, madeBy);
        this.ownedAt = ownedAt;
        this.ownedFrom = ownedFrom;
        this.price = price;
    }

    /*
     * Constructor for PropertyDAO
     */
    public Property(String id, String label, String description, String period, double height, double width, double depth, double weight, Date deletedAt, User deletedBy, Collection collection, Category category, ArrayList<Artist> madeBy, ArrayList<Picture> pictures, Date ownedAt, String ownedFrom, double price, PropertyState propertyState) {
        super(id, label, description, period, height, width, depth, weight, deletedAt, deletedBy, collection, category, madeBy, pictures);
        this.ownedAt = ownedAt;
        this.ownedFrom = ownedFrom;
        this.price = price;
        this.propertyState = propertyState;
    }

    public Date getOwnedAt() {
        return ownedAt;
    }

    public void setOwnedAt(Date ownedAt) {
        this.ownedAt = ownedAt;
    }

    public String getOwnedFrom() {
        return ownedFrom;
    }

    public void setOwnedFrom(String ownedFrom) {
        this.ownedFrom = ownedFrom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Property n°" + id + " - Initial price : " + price;
    }
}
