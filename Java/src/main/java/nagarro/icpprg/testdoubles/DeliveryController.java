package nagarro.icpprg.testdoubles;

import java.time.Duration;
import java.util.List;

public class DeliveryController {
    private static final long ACCEPTED_DELAY_IN_SECONDS = 3600 * 10;
    private IMessageGateway emailGateway;
    private IMapService mapService;
    public List<Delivery> deliverySchedule;

    public DeliveryController(IMessageGateway emailGateway, IMapService mapService, List<Delivery> deliverySchedule) {
        this.emailGateway = emailGateway;
        this.mapService = mapService;
        this.deliverySchedule = deliverySchedule;
    }

    public void updateDelivery(DeliveryEvent deliveryEvent) {
        Delivery nextDelivery = null;
        for (int i = 0; i < deliverySchedule.size(); i++) {
            Delivery delivery = deliverySchedule.get(i);
            if (deliveryEvent.getId() == delivery.getId()) {
                delivery.setArrived(true);
                var timeDifference = Duration.between(deliveryEvent.getTimeOfDelivery(), delivery.getTimeOfDelivery());
                if (timeDifference.getSeconds() <= ACCEPTED_DELAY_IN_SECONDS) {
                    delivery.setOnTime(true);
                }

                delivery.setTimeOfDelivery(deliveryEvent.getTimeOfDelivery());
                String message = String.format("Regarding your delivery today at %s. How likely would you be to recommend this delivery service to a friend? Click <a href='url'>here</a>", delivery.getTimeOfDelivery());
                emailGateway.send(delivery.getContactEmail(), "Your feedback is important to us", message);
                if (deliverySchedule.size() > i + 1) {
                    nextDelivery = deliverySchedule.get(i + 1);
                }

                if (!delivery.isOnTime() && deliverySchedule.size() > 1 && i > 0) {
                    var previousDelivery = deliverySchedule.get(i - 1);
                    var elapsedTime = Duration.between(delivery.getTimeOfDelivery(), previousDelivery.getTimeOfDelivery());
                    mapService.updateAverageSpeed(previousDelivery.getLocation(), delivery.getLocation(), elapsedTime);
                }
            }
        }

        if (nextDelivery != null) {
            var nextEta = mapService.calculateETA(deliveryEvent.getLocation(), nextDelivery.getLocation());
            var message = String.format("Your delivery to %s is next, estimated time of arrival is in %d minutes. Be ready!", nextDelivery.getLocation(), nextEta);
            emailGateway.send(nextDelivery.getContactEmail(), "Your delivery will arrive soon", message);
        }
    }
}
