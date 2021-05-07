package bean;

public class ArtistWork {
    private Work work;
    private Artist artist;

    public ArtistWork() {
    }

    public ArtistWork(Work work, Artist artist) {
        this.work = work;
        this.artist = artist;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
