package nagarro.icpprg.testdoubles;

import java.time.Duration;

public interface IMapService {
    void updateAverageSpeed(Location location, Location location1, Duration elapsedTime);

    double calculateETA(Location location, Location location1);
}
