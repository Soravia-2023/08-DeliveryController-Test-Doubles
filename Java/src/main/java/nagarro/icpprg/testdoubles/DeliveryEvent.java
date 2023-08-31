package nagarro.icpprg.testdoubles;

import java.time.LocalDateTime;

public class DeliveryEvent {

    private long Id;
    private Location location;
    private LocalDateTime timeOfDelivery;

    public DeliveryEvent(long id, Location location, LocalDateTime timeOfDelivery) {
        Id = id;
        this.location = location;
        this.timeOfDelivery = timeOfDelivery;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
}
