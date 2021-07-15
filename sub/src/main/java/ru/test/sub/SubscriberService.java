package ru.test.sub;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.sub.database.PurchaseMessage;
import ru.test.sub.database.PurchaseRepository;
import ru.test.sub.database.SubscriptionMessage;
import ru.test.sub.database.SubscriptionRepository;

import java.sql.Timestamp;
import java.util.logging.Logger;

@Service
public class SubscriberService {
    private final SubscriptionRepository subscriptionRepository;
    private final PurchaseRepository purchaseRepository;
    Logger log = Logger.getLogger(SubscriberService.class.getName());

    public SubscriberService(@Autowired SubscriptionRepository subscriptionRepository, @Autowired PurchaseRepository purchaseRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void saveMessage(String message) {
        log.info("Subscriber: save message " + message);
        JSONObject parsedMessage = new JSONObject(message);
        MessageType messageType = parsedMessage.getEnum(MessageType.class, "action");
        Long id = parsedMessage.getLong("id");
        int msisdn = parsedMessage.getInt("msisdn");
        Timestamp timestamp = new Timestamp(parsedMessage.getLong("timestamp"));
        if (messageType.equals(MessageType.SUBSCRIPTION)){
            subscriptionRepository.save(new SubscriptionMessage(id, msisdn, timestamp));
        } else if (messageType.equals(MessageType.PURCHASE)) {
            purchaseRepository.save(new PurchaseMessage(id, msisdn, timestamp));
        }
    }
}
