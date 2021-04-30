package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Work {
    protected String id, label, description, period;
    protected double height, width, depth, weight;
    protected Date deletedAt;
    protected User deletedBy;
    protected Collection collection;
    protected Category category;
    protected ArrayList<Artist> madeBy;
    protected ArrayList<Picture> pictures;

    public Work() {
    }

    /*
     * Constructor for creating a Work that does not exists yet in DB
     */
    public Work(String label, String description, String period, double height, double width, double depth, double weight, Collection collection, Category category, ArrayList<Artist> madeBy) {
        this.id = UUID.randomUUID().toString();
        this.label = label;
        this.description = description;
        this.period = period;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
        this.collection = collection;
        this.category = category;
        this.madeBy = madeBy;
    }

    /*
     * Constructor for WorkDAO
     */
    public Work(String id, String label, String description, String period, double height, double width, double depth, double weight, Date deletedAt, User deletedBy, Collection collection, Category category, ArrayList<Artist> madeBy, ArrayList<Picture> pictures) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.period = period;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
        this.collection = collection;
        this.category = category;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
        this.madeBy = madeBy;
        this.pictures = pictures;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Artist> getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(ArrayList<Artist> madeBy) {
        this.madeBy = madeBy;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Work n°" + id + " - " + label;
    }
}
