package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-31
 */
@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Component
    public class Sender {

        @Autowired
        private AmqpTemplate rabbitTemplate;

        public void send(int i) {
            String context = i+"hello " + new Date();
            //log.info(context);
            this.rabbitTemplate.convertAndSend("hello", context);
        }

    }

    @Component
    @RabbitListener(queues = "hello")
    public class Receiver {

        @RabbitHandler
        public void process(String hello) {
            log.info("receiver-->{}",hello);
        }

    }

    @Configuration
    public class RabbitConfig {

        @Bean
        public Queue helloQueue() {
            return new Queue("hello");
        }

    }
}
