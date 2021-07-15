package ru.test.pub;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.test.pub.generator.JSONMessageGenerator;
import ru.test.pub.generator.JSONMessageGeneratorImpl;

import java.util.logging.Logger;

@Component
public class Publisher {
    private final JSONMessageGenerator messageGenerator;
    private final Sender sender;
    private final int NUMBER_OF_THREADS = 5;
    Logger log = Logger.getLogger(Publisher.class.getName());

    public Publisher(@Autowired JSONMessageGeneratorImpl messageGenerator, @Autowired Sender sender) {
        this.messageGenerator = messageGenerator;
        this.sender = sender;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startToSendMessages() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(messageGenerator);
            while (true) {
                JSONObject message = messageGenerator.nextMessage();
                log.info(threadName + " : generate message " + message);
                sender.sendMessage(message);
                log.info(threadName + " : send message");
                synchronized (messageGenerator) {
                    try {
                        messageGenerator.wait(15_000);
                    } catch (InterruptedException e) {
                        log.warning(e.getMessage());
                    }
                }
            }
        };
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
