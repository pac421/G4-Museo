package bean;

public class Picture {
    private String id, extension;
    private Work work;

    public Picture() {
    }

    /*
     * Constructor for creating a Picture that does not exists yet in DB
     */
    public Picture(String extension, Work work) {
        this.extension = extension;
        this.work = work;
    }

    /*
     * Constructor for PictureDAO
     */
    public Picture(String id, String extension, Work work) {
        this.id = id;
        this.extension = extension;
        this.work = work;
    }

    public String getId() {
        return id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }


    @Override
    public String toString() {
        return "Picture : " + id + "." + extension;
    }
}
