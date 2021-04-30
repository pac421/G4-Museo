package bean;

import java.util.UUID;

public class Artist {
    private String id, firstname, lastname, period;

    public Artist() {
    }

    public Artist(String firstname, String lastname, String period) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.period = period;
    }

    public Artist(String id, String firstname, String lastname, String period) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return id + " - " + firstname + " - " + lastname + " - " + period;
    }
}