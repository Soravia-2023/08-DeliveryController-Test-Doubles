package nagarro.icpprg.testdoubles;

import java.time.LocalDateTime;

public class Delivery {
    private long Id;
    private String contactEmail;
    private Location location;
    private LocalDateTime timeOfDelivery;
    private boolean arrived;
    private boolean onTime;

    public Delivery(long id, String contactEmail, Location location, LocalDateTime timeOfDelivery, boolean arrived, boolean onTime) {
        Id = id;
        this.contactEmail = contactEmail;
        this.location = location;
        this.timeOfDelivery = timeOfDelivery;
        this.arrived = arrived;
        this.onTime = onTime;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(LocalDateTime timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public boolean isOnTime() {
        return onTime;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }
}
