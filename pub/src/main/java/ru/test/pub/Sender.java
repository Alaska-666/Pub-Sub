package ru.test.pub;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class Sender {
    private final EurekaClient eurekaClient;
    private final String subscriberName;

    public Sender(@Autowired EurekaClient eurekaClient, @Value("${subscriberName}") String subscriberName) {
        this.eurekaClient = eurekaClient;
        this.subscriberName = subscriberName;
    }

    public void sendMessage(JSONObject message) {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(subscriberName, false);
        String requestUrl = UriComponentsBuilder.fromUri(URI.create(instance.getHomePageUrl())).toUriString();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(requestUrl);
        try {
            StringEntity params = new StringEntity(message.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
