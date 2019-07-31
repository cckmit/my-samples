package com.example;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-17
 */

@RestController
@SpringBootApplication
@EnableBinding(TestTopic.class)
public class SendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SendApplication.class,args);
    }

    @Autowired
    private TestTopic testTopic;

    @GetMapping("/")
    public String send(String msg)throws Exception{

        boolean isSendV1 = testTopic.output().send(MessageBuilder.withPayload(msg).setHeader("version", "1.0").build());
        boolean isSendV2 = testTopic.output().send(MessageBuilder.withPayload(msg).setHeader("version", "2.0").build());

        CustomerCreatedEvent event = new CustomerCreatedEvent();
        event.setEventType("CustomerCreatedEvent");
        JSONObject object = new JSONObject();
        object.put("eventType","CustomerCreatedEvent");
        object.put("num",10);

        testTopic.output().send(MessageBuilder.withPayload(object.toString()).build());

        return msg;
    }
}


