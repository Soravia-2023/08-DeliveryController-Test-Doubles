package nagarro.icpprg.testdoubles;

import java.time.Duration;

public class MapService implements IMapService {
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final double R = 6373.0;
    // in km/h
    private double averageSpeed = 50.0;

    @Override
    public double calculateETA(Location location1, Location location2) {
        var distance = this.calculateDistance(location1, location2);
        return distance / this.averageSpeed * MINUTES_PER_HOUR;
    }

    @Override
    public void updateAverageSpeed(Location location1, Location location2, Duration elapsedTime) {
        var distance = this.calculateDistance(location1, location2);
        var updatedSpeed = distance / (elapsedTime.getSeconds() / SECONDS_PER_HOUR);
        this.averageSpeed = updatedSpeed;
    }

    private double calculateDistance(Location location1, Location location2) {
        var d1 = location1.getLatitude() * (Math.PI / 180.0);
        var num1 = location1.getLongitude() * (Math.PI / 180.0);
        var d2 = location2.getLatitude() * (Math.PI / 180.0);
        var num2 = location2.getLongitude() * (Math.PI / 180.0) - num1;
        var d3 = Math.pow(Math.sin((d2 - d1) / 2.0), 2.0) + Math.cos(d1) * Math.cos(d2) * Math.pow(Math.sin(num2 / 2.0), 2.0);

        return R * (2.0 * Math.atan2(Math.sqrt(d3), Math.sqrt(1.0 - d3)));
    }

}
