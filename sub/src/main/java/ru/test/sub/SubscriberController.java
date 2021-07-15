package ru.test.sub;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(@Autowired SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public void saveJSONMessage(@RequestBody String s) {
        subscriberService.saveMessage(s);
    }
}
