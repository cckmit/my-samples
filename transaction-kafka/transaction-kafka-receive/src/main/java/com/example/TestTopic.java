package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-17
 */
public interface TestTopic {
    String OUTPUT = "example-topic-output";
    String INPUT = "example-topic-input";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}
