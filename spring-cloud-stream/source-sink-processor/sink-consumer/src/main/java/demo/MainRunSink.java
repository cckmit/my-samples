package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
@SpringBootApplication
@EnableBinding(Sink.class)
@Slf4j
public class MainRunSink {

    public static void main(String[] args) {
        SpringApplication.run(MainRunSink.class, args);
    }

    //@StreamListener(Sink.INPUT)
    public void receiveX(String data) {
        log.info("Data received from customer-0..." + data);
    }

    @StreamListener(value = Sink.INPUT,condition = "headers['eventType']=='event1'")
    public void receiveX1(String data) {
        log.info("Data received from event1..." + data);
    }

    @StreamListener(value = Sink.INPUT,condition = "headers['eventType']=='event2'")
    public void receiveX2(String data) {
        log.info("Data received from event2..." + data);
    }

}
