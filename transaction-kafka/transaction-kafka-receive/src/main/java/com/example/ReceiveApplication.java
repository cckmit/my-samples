package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-17
 */

@RestController
@SpringBootApplication
@EnableBinding(TestTopic.class)
public class ReceiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveApplication.class,args);
    }

    @Autowired
    private TestTopic testTopic;

    @Slf4j
    @Component
    static class TestListener {

        @StreamListener(value = TestTopic.INPUT, condition = "headers['version']=='1.0'")
        public void receiveV1(String payload, @Header("version") String version) {
            log.info("Received v1 : " + payload + ", " + version);
        }

        @StreamListener(value = TestTopic.INPUT, condition = "headers['version']=='2.0'")
        public void receiveV2(String payload, @Header("version") String version) {
            log.info("Received v2 : " + payload + ", " + version);
        }

        //@EventHandler(condition="payload.eventType=='CustomerCreatedEvent'")
        @StreamListener(value = TestTopic.INPUT, condition="payload.eventType=='CustomerCreatedEvent'")
        //@StreamListener(value = TestTopic.INPUT)
        public void handleCustomerEvent(@Payload String payload) {
            log.info("event type-{}",payload);
        }

    }
}


