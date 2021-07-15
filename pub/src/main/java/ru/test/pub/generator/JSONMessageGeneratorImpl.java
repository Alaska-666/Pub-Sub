package ru.test.pub.generator;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JSONMessageGeneratorImpl implements JSONMessageGenerator {
    private final AtomicLong id = new AtomicLong(0);
    private final int MAX_MSIDSN = 999999999;
    private final int MIN_MSIDSN = 100000000;

    private int generateRandomMsisdn() {
        Random random = new Random();
        return random.ints(MIN_MSIDSN, MAX_MSIDSN).findFirst().orElse(MIN_MSIDSN);
    }

    private MessageType generateRandomMessageType() {
        return MessageType.values()[new Random().nextInt(MessageType.values().length)];
    }

    @Override
    public JSONObject nextMessage() {
        JSONObject message = new JSONObject();
        message.put("id", id.getAndIncrement());
        message.put("msisdn", generateRandomMsisdn());
        message.put("action", generateRandomMessageType());
        message.put("timestamp", System.currentTimeMillis());
        return message;
    }
}
